package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.StockDao;
import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.service.StockService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;


public class StockServiceImpl implements StockService {

    SqlSession sqlSession = null;
    StockDao dao = null;

    @Override
    public List<Stock> getStock() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        System.out.println(dao);
        List<Stock> list = dao.getStock();
        //sqlSession.commit();
         sqlSession.close();
        return list;
    }



    @Override
    public List<Stock> selectDate(String date) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        List<Stock> list = dao.selectDate(date);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Stock> selectYear(String date) {

        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        List<Stock> list = dao.selectYear(date);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Stock> selectMonth(String year,String month) {

        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        List<Stock> list = dao.selectMonth(year,month);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Stock> selectDateRange(String start, String end) {

        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        List<Stock> list = dao.selectDateRange(start,end);
        sqlSession.close();
        return list;
    }

    @Override
    public double getInputToday() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        double input = dao.getInputToday();
        sqlSession.close();
        return input;
    }

    //获取近七天供应商供货情况
    @Override
    public List<Map<String, Object>> findSupplyInput() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(StockDao.class);
        List<Map<String,Object>> list = dao.findSupplyInput();
        sqlSession.close();
        return list;
    }
}
