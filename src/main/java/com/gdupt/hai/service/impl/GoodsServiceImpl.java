package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.GoodsDao;
import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.service.GoodsService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService {

    SqlSession sqlSession = null;
    GoodsDao dao = null;

    /**
     * 查询所有商品列表
     * @return
     */
    @Override
    public List<Goods> getAllGoods() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        List<Goods> list = dao.selectAllGoods();
        sqlSession.close();
        return list;
    }

    /**
     * 按分页查询商品列表
     * @param page
     * @param size
     * @return
     */
    @Override
    public Pager<Goods> getAllGoods(int page, int size) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        Pager<Goods> pager = new Pager<Goods>();
        List<Goods> list = dao.selectAllGoods(params);
        pager.setRows(list);
        pager.setTotal(dao.count());
        sqlSession.close();
        return pager;
    }

    /**
     * 根据商品名称查询商品信息
     * @param name
     * @return
     */
    @Override
    public List<Goods> queryGoods(String name) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        List<Goods> list = dao.getGoodsByName(name);
        sqlSession.close();
        return list;
    }

    /**
     * 根据商品编号删除商品
     * @param goodsId
     * @return
     */
    @Override
    public int deleteGoods(String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        int result = dao.removeGoods(goodsId);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    /**
     * 通过商品编号修改相应的商品信息
     * @param goods
     * @return
     */
    @Override
    public int modifyGoods(Goods goods) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        int result = dao.editGoods(goods);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public Goods isGoodsExist(String goodsId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        Goods goods = dao.isGoodsExist(goodsId);
        return goods;
    }

    @Override
    public int addNewGoods(Goods goods) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        int result = dao.addNewGoods(goods);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public List<Map<String, Object>> getCateList() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(GoodsDao.class);
        List<Map<String,Object>> list = dao.getCategory();
        sqlSession.close();
        return list;
    }


}
