package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.ProfferDao;
import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Proffer;
import com.gdupt.hai.service.ProfferService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfferServiceImpl implements ProfferService {

    SqlSession sqlSession = null;
    ProfferDao dao = null;

    /**
     * 获取供应商信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public Pager<Proffer> getAllSupply(int page, int size) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        Pager<Proffer> pager = new Pager<Proffer>();
        List<Proffer> list = dao.selectAllSupply(params);
        pager.setRows(list);
        pager.setTotal(dao.count());
        sqlSession.close();
        return pager;
    }

    /**
     * 判断供应商信息是否已存在
     * @param profferId
     * @param fullName
     * @return
     */
    @Override
    public Proffer exitProffer(String profferId, String fullName) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        Proffer proffer = dao.exitProffer(profferId,fullName);
        return proffer;
    }

    /**
     * 添加供应商信息
     * @param proffer
     * @return
     */
    @Override
    public int addNewProffer(Proffer proffer) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        int result = dao.addNewProffer(proffer);
        sqlSession.commit();   //提交事物
        sqlSession.close();
        return result;
    }

    /**
     * 修改供应商信息
     * @param proffer
     * @return
     */
    @Override
    public int editProffer(Proffer proffer) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        int result = dao.editProffer(proffer);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    /**
     * 删除供应商
     * @param profferId
     * @return
     */
    @Override
    public int removeProffer(String profferId) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        int result = dao.removeProffer(profferId);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    /**
     * 根据名称查询供应商
     * @param profferName
     * @return
     */
    @Override
    public List<Proffer> getProfferByName(String profferName) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(ProfferDao.class);
        List<Proffer> list = dao.getProfferByName(profferName);
        sqlSession.close();
        return list;
    }
}
