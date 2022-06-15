package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockDao {

    List<Stock> getStock();

    List<Stock> selectDate(String date);

    List<Stock> selectYear(String date);

    List<Stock> selectMonth(@Param("year") String year, @Param("month") String month);

    List<Stock> selectDateRange(@Param("startDate") String startDate,@Param("endDate") String endDate);

    //获取当天进货量
    double getInputToday();

    //获取近七天的供应商供货情况
    List<Map<String,Object>> findSupplyInput();
}

