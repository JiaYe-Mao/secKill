package Dao;

import entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {

        int result = successKilledDao.insertSuccessKilled(1001L, 12345678L);
        System.out.println("result: "+ result);
    }

    @Test
    public void queryByIdWithGoodsDetails() {
        SuccessKilled successKilled = successKilledDao.queryByIdWithGoodsDetails(1001L, 12345678L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());
    }
}