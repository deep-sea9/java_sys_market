package com.gdupt.hai.service;

import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.domain.Inventory;
import com.gdupt.hai.domain.Pager;

import java.util.List;

public interface InventoryService {

    //获取库存列表，分页操作
    Pager<Inventory> getInventory(int page,int size);

    List<Inventory> queryInventory(String goodsName,String goodsId);

    //删除库存
    int removeInventory(String goodsId);

    //添加库存
    int addInventory(Stock stock);

    //更新库存
    int updateInventory(String date,int count,String goodsId);

    //判断该商品库存是否已存在
    List<Inventory> exitInventory(String goodsId);

    //添加库存
    int increaseInventory(Inventory inventory);

}
