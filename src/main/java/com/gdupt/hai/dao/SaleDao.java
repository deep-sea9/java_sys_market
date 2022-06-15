package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleDao {
    //查询所有销售情况，分页显示
    List<Sale> getSales(Map<String,Object> params);

    int count();

    //根据商品名称查找销售记录
    List<Sale> getSaleByName(String goodsName);

    List<Sale> findSaleDate(String date);

    List<Sale> findSaleYear(String year);

    List<Sale> findSaleMonth(@Param("year") String year,@Param("month")String month);

    List<Sale> findSaleRange(@Param("start") String start,@Param("end") String end);

    /**
     * 根据日期查找销售前5名
     * @return
     */
    List<Map<String,Object>> findSales(String date);

    /**
     * 根据日期查找统计分类销量
     * @return
     */
    List<Map<String,Object>> findCate();

    //获取当天的销售额
    double getSalesToday();

    //获取本周销售额额
    double getSaleWeek();

    //获取本月销售额
    double getSaleMonth();

    //获取本季度销售额
    double getSaleQuarter();

    //过去7天的销售额
    List<Map<String,Object>> findSaleSevenDay();

    //今年各个月份的销售额统计
    List<Map<String,Object>> getSalesMonth();
}
