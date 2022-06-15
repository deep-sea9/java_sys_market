package com.gdupt.hai.service;

import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Proffer;

import java.util.List;

public interface ProfferService {

    //获取商品列表，分页操作
    public Pager<Proffer> getAllSupply(int page, int size);

    //判断供应商信息是否已存在
    Proffer exitProffer(String profferId,String fullName);

    //添加新的供应商信息
    int addNewProffer(Proffer proffer);

    //修改供应商信息
    int editProffer(Proffer proffer);

    //删除供应商
    int removeProffer(String profferId);

    //查询供应商
    List<Proffer> getProfferByName(String profferName);
}
