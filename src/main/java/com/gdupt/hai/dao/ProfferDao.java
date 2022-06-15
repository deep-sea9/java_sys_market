package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Proffer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProfferDao {

    //获取所有供应商信息
    List<Proffer> selectAllSupply(Map<String,Object> params);

    int count();

    //判断供应商是否已存在
    Proffer exitProffer(@Param("profferId")String profferId,@Param("fullName")String fullName);

    //添加供应商
    int addNewProffer(Proffer proffer);

    //修改供应商信息
    int editProffer(Proffer proffer);

    //删除供应商
    int removeProffer(String profferId);

    //根据名称查询供应商
    List<Proffer> getProfferByName(String profferName);

}
