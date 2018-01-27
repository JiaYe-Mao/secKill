package Dao;

import entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledMapperTest {

    @Autowired
    SuccessKilledMapper successKilledMapper;

    @Test
    public void insertSuccessKilled() {

        int result = successKilledMapper.insertSuccessKilled(1001L, 12345678L);
        System.out.println("result: "+ result);
    }

    @Test
    public void queryByIdWithGoodsDetails() {
        SuccessKilled successKilled = successKilledMapper.queryByIdWithGoodsDetails(1001L, 12345678L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());
    }
}