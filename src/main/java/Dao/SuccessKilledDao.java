package Dao;


import entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {

    /**
     * 插入一行购买明细, 可过滤重复
     * @param secKilledId
     * @param phoneNumber
     * @return 插入行数
     */
    int insertSuccessKilled(@Param("secKilledId") long secKilledId, @Param("phoneNumber") long phoneNumber);

    /**
     * 根据id查询并返回购买明细和商品信息(在SuccessKilled实体里已经封装了一个SecKill对象)
     * @param phoneNumber
     * @param secKilledId
     * @return
     */
    SuccessKilled queryByIdWithGoodsDetails(@Param("secKilledId") long secKilledId, @Param("phoneNumber") long phoneNumber);
}
