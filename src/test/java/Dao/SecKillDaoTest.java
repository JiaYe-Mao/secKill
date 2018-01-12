package Dao;

import entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {

    @Autowired
    SecKillDao secKillDao;

    @Test
    public void reduceNumber() {
        Calendar calendar =  new Calendar.Builder().build();
        calendar.set(2018,0,11,2,3);
        Date date = calendar.getTime();
        int result = secKillDao.reduceNumber(1000L, date);
        System.out.println("result: " + result);
    }

    @Test
    public void queryById() {
        long id = 1000;
        SecKill secKill = secKillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
    }

    @Test
    public void queryAll() {
        List<SecKill> list = secKillDao.queryAll(1,100);
        for (SecKill secKill : list){
            System.out.println(secKill);
        }
    }
}