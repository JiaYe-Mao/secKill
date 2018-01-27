package service.impl;

import Dao.SecKillMapper;
import Dao.SuccessKilledMapper;
import Dto.Exposer;
import Dto.SecKillExecution;
import entity.SecKill;
import entity.SuccessKilled;
import enums.SecKillStateEnum;
import exception.RepeatKilledException;
import exception.SecKillCloseException;
import exception.SecKillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import service.SecKillService;

import java.util.Date;
import java.util.List;

@Service
public class SecKillServiceImpl implements SecKillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillMapper secKillMapper;

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    private final String salt = "sdljfo23o58$ut0-991kgkdl3$%#$nbv09801*%^$";

    @Override
    public List<SecKill> getSecKillList() {
        return secKillMapper.queryAll(0,4);
    }

    @Override
    public SecKill getSecKillById(long secKillId) {
        return secKillMapper.queryById(secKillId);
    }

    @Override
    public Exposer exportSecKillUrl(long secKillId) {
        SecKill secKill = getSecKillById(secKillId);
        if (secKill == null){
            return new Exposer(false, secKillId);
        }

        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, secKillId, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(secKillId);
        return new Exposer(true, md5, secKillId);

    }

    @Override
    @Transactional
    /**
     * 1.需要保证事务尽可能短, 不要穿插其他的网络操作, 比如RPC/HTTP, 否则会影响并发性(行级锁在事务过程中一直存在),
     *   可以把它们先做完耗时操作再调用事务;
     * 2.不是所有方法都需要事务, 比如只有单个操作的就不需要;
     */
    public SecKillExecution executeSecKill(long secKillId, long phoneNumber, String md5)
                throws SecKillException, RepeatKilledException, SecKillCloseException {
        if (md5 == null || !md5.equals(getMd5(secKillId))){
            throw new SecKillException("secKill data rewrite!");
        }
        try {
            int updateCount = secKillMapper.reduceNumber(secKillId, new Date());
            if (updateCount <= 0){
                throw new SecKillCloseException("secKill closed");
            }
            int insertCount = successKilledMapper.insertSuccessKilled(secKillId, phoneNumber);
            if (insertCount <= 0){
                throw new RepeatKilledException("secKill repeated");
            }
            SuccessKilled successKilled = successKilledMapper.queryByIdWithGoodsDetails(secKillId, phoneNumber);
            return new SecKillExecution(secKillId, SecKillStateEnum.SUCCESS, successKilled);
        } catch (RepeatKilledException e1){
            throw e1;
        } catch (SecKillCloseException e2){
            throw e2;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SecKillException("secKill inner exception" + e.getMessage());
        }

    }

    private String getMd5(long secKillid){
        String base = secKillid + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
