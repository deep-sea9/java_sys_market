package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.SaleDao;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Sale;
import com.gdupt.hai.service.SaleService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleServiceImpl implements SaleService {

    SqlSession sqlSession = null;
    SaleDao dao = null;

    @Override
    public Pager<Sale> getSales(int page, int size) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        Pager<Sale> pager = new Pager<Sale>();
        List<Sale> list = dao.getSales(params);
        pager.setRows(list);
        pager.setTotal(dao.count());
        return pager;
    }

    @Override
    public List<Sale> getSaleByName(String goodsName) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Sale> list = dao.getSaleByName(goodsName);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Sale> findSaleDate(String date) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Sale> list = dao.findSaleDate(date);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Sale> findSaleYear(String year) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Sale> list = dao.findSaleYear(year);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Sale> findSaleMonth(String year,String month) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Sale> list = dao.findSaleMonth(year,month);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Sale> findSaleRange(String start, String end) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Sale> list = dao.findSaleRange(start,end);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Map<String,Object>> findSales(String date) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Map<String,Object>> list = dao.findSales(date);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Map<String, Object>> findCate() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Map<String, Object>> list = dao.findCate();
        sqlSession.close();
        return list;
    }

    /**
     * 获取当天的销售额
     * @return
     */
    @Override
    public double getSalesToday() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        double sales = dao.getSalesToday();
        sqlSession.close();
        return sales;
    }

    @Override
    public Map<String, Double> getSalesDate() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        Map<String,Double> map = new HashMap<>();
        map.put("todaySales",dao.getSalesToday());
        map.put("weekSales",dao.getSaleWeek());
        map.put("monthSales",dao.getSaleMonth());
        map.put("quarterSales",dao.getSaleQuarter());
        return map;
    }

    /**
     * 获取过去7天的销售额
     */
    @Override
    public List<Map<String,Object>> findSaleSevenDay() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Map<String,Object>> list = dao.findSaleSevenDay();
        sqlSession.close();
        return list;
    }

    @Override
    public List<Map<String, Object>> getSalesMonth() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(SaleDao.class);
        List<Map<String,Object>> list = dao.getSalesMonth();
        sqlSession.close();
        return list;
    }
}
