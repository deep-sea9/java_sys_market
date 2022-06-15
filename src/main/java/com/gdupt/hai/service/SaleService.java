package com.gdupt.hai.service;

import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleService {

    //获取销售列表，分页操作
     Pager<Sale> getSales(int page, int size);

    /**
     * 根据商品名称查询销售记录
     */
    List<Sale> getSaleByName(String goodsName);

    List<Sale> findSaleDate(String date);

    List<Sale> findSaleYear(String year);

    List<Sale> findSaleMonth(String year,String month);

    List<Sale> findSaleRange(String start,String end);

    /**
     * 根据日期查找销售额前5名
     * @return
     */
    List<Map<String,Object>>  findSales(String date);

    /**
     * 根据日期分类统计销量
     * @return
     */
    List<Map<String,Object>>  findCate();

    //获取当天的销售额
    double getSalesToday();

    Map<String,Double> getSalesDate();

    //查询过去7天的销售额
    List<Map<String,Object>> findSaleSevenDay();

    //今年各个月份的销售额统计
    List<Map<String,Object>> getSalesMonth();

}
