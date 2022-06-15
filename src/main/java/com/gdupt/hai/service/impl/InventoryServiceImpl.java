package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.InventoryDao;
import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.domain.Inventory;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.service.InventoryService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    SqlSession sqlSession = null;
    InventoryDao dao = null;

    @Override
    public Pager<Inventory> getInventory(int page, int size) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        Pager<Inventory> pager = new Pager<Inventory>();
        List<Inventory> list = dao.getInventory(params);
        pager.setRows(list);
        pager.setTotal(dao.count());
        sqlSession.close();
        return pager;
    }

    @Override
    public List<Inventory> queryInventory(String goodsName, String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        List<Inventory> list = dao.queryInventory(goodsName,goodsId);
        sqlSession.close();
        return list;
    }

    @Override
    public int removeInventory(String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        int result = dao.removeInventory(goodsId);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public int addInventory(Stock stock) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        int result = dao.addInventory(stock);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public int updateInventory(String date, int count, String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        int result = dao.updateInventory(date,count,goodsId);
        sqlSession.commit();
        sqlSession.close();
        return 0;
    }

    @Override
    public List<Inventory> exitInventory(String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        List<Inventory> list = dao.exitInventory(goodsId);
        sqlSession.close();
        return list;
    }

    @Override
    public int increaseInventory(Inventory inventory) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(InventoryDao.class);
        int result = dao.increaseInventory(inventory);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }


}
