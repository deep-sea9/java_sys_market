package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsDao {

    //查询所有商品
    List<Goods> selectAllGoods();

    List<Goods> selectAllGoods(Map<String,Object> params);

    int count();

    //通过商品名称查询商品信息
    List<Goods> getGoodsByName(String goodsName);

    //根据商品编号删除商品
    int removeGoods(String goodsId);

    //修改商品信息
    int editGoods(Goods goods);

    //通过商品编号和名称验证商品是否存在
    Goods isGoodsExist(String goodsId);

    //添加新商品
    int addNewGoods(Goods goods);

    //获取商品分类
    List<Map<String,Object>> getCategory();
}
