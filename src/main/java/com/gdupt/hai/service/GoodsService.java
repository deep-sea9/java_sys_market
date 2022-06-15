package com.gdupt.hai.service;

import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    //获取全部商品列表
    List<Goods> getAllGoods();

    //获取商品列表，分页操作
    public Pager<Goods> getAllGoods(int page,int size);

    //通过商品名称查询商品信息
    List<Goods> queryGoods(String name);

    //通过商品编号删除商品
    int deleteGoods(String goodsId);

    //通过商品编号修改相应的商品信息
    int modifyGoods(Goods goods);

    //判断商品是否存在
    Goods isGoodsExist(String goodsId);

    //添加新的商品
    int addNewGoods(Goods goods);

    //获取所有分类名
    List<Map<String,Object>> getCateList();

}
