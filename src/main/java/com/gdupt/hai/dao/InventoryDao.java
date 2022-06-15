package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.domain.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InventoryDao {
    public List<Inventory> getInventory(Map<String,Object> params);

    public int count();

    List<Inventory> queryInventory(@Param("goodsName") String goodsName,@Param("goodsId")String goodsId);

    int removeInventory(String goodsId);

    int addInventory(Stock stock);

    int updateInventory(@Param("date")String date,@Param("count")int count,@Param("goodsId")String goodsId);

    //判断该商品库存是否已存在
    List<Inventory> exitInventory(String goodsId);

    //添加库存
    int increaseInventory(Inventory inventory);


}
