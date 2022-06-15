package com.gdupt.hai.service;

import com.gdupt.hai.domain.Stock;

import java.util.List;
import java.util.Map;

public interface StockService {

    List<Stock> getStock();

    List<Stock> selectDate(String date);

    List<Stock> selectYear(String date);

    List<Stock> selectMonth(String year,String month);

    List<Stock> selectDateRange(String start,String end);

    //获取当天进货量
    double getInputToday();

    //获取近七天的供应商供货情况
    List<Map<String,Object>> findSupplyInput();
}
