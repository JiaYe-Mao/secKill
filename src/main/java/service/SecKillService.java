package service;

import Dto.Exposer;
import Dto.SecKillExecution;
import entity.SecKill;
import exception.RepeatKilledException;
import exception.SecKillCloseException;
import exception.SecKillException;

import java.util.List;

public interface SecKillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<SecKill> getSecKillList();

    /**
     * 查询单个记录
     * @param secKillId
     * @return
     */
    SecKill getSecKillById(long secKillId);

    /**
     * 秒杀开启时输出接口地址, 否则输出系统时间和秒杀时间
     * @param secKillId
     */
    Exposer exportSecKillUrl(long secKillId);

    SecKillExecution executeSecKill(long secKillId, long phoneNumber, String md5) throws SecKillException, RepeatKilledException, SecKillCloseException;

}
