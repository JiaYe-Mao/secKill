package Dao;

import entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecKillDao {

    /**
     * 减库存(GoodsNumber)
     * @param secKillId
     * @param killTime
     * @return 影响的更新语句数
     */
    int reduceNumber(@Param("secKillId") long secKillId, @Param("killTime") Date killTime);

    /**
     * 查询列表中的一行
     * @param secKillId
     * @return
     */
    SecKill queryById(long secKillId);

    /**
     * 查询表中的一部分
     * @param offset
     * @param limit
     * @return
     */
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
