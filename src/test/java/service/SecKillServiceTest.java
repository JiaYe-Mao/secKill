package service;

import Dto.Exposer;
import Dto.SecKillExecution;
import entity.SecKill;
import exception.RepeatKilledException;
import exception.SecKillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class SecKillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSecKillList() {
        List<SecKill> list = secKillService.getSecKillList();
        logger.info("list = {}", list);
    }

    @Test
    public void getSecKillById() {
        long id = 1000;
        SecKill secKill = secKillService.getSecKillById(id);
        logger.info("secKill = {}", secKill);
    }

    @Test
    public void exportSecKillUrl() {
        long id = 1001;
        Exposer exposer = secKillService.exportSecKillUrl(id);
        logger.info("exposer = {}", exposer);

        if (exposer.isExposed()){
            long phoneNumber = 123456;
            String md5 = exposer.getMd5();
            try {
                SecKillExecution secKillExecution = secKillService.executeSecKill(id, phoneNumber, md5);
                logger.info("secKillExecution = {}", secKillExecution);
            } catch (RepeatKilledException e1){
                logger.error(e1.getMessage());
            } catch (SecKillCloseException e2){
                logger.error(e2.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }


}