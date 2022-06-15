package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.service.GoodsService;
import com.gdupt.hai.service.impl.GoodsServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;


@WebServlet(name = "GoodsServlet")
public class GoodsServlet extends BaseServlet {

    GoodsService service = new GoodsServiceImpl();
    /**
     * 获取全部商品列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getGoodsList(HttpServletRequest request,HttpServletResponse response) throws Exception{
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        PrintWriter writer = response.getWriter();
        Pager<Goods> pager = service.getAllGoods(page,size);
        List<Map<String,Object>> list = service.getCateList();
        JSONObject jsonObject = new JSONObject();
        if (pager != null){
            jsonObject.put("goodsList",pager);
            jsonObject.put("cateList",list);
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",400);
        }
        writer.println(jsonObject);
        writer.flush();
        writer.close();
    }

    /**
     * 获取分类
     * @param request
     * @param response
     * @throws Exception
     */
    public void getCateList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        JSONObject object = new JSONObject();
        List<Map<String,Object>> list = service.getCateList();
        object.put("cateList",list);
        response.getWriter().print(object);
    }

    /**
     * 通过名称查找对应商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryGoodsList(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String goodsName = request.getParameter("goodsName");
        System.out.println(goodsName);
        List<Goods> goodsList = service.queryGoods(goodsName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsList",goodsList);
        if (goodsList.isEmpty()){
            jsonObject.put("status",400);
        }
        else {
            jsonObject.put("status",200);
        }
        response.getWriter().println(jsonObject);
    }

    /**
     *
     * 通过商品编号删除商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteGoods(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        String goodsId = request.getParameter("goodsId");
        System.out.println("----"+goodsId+"------");
        int result = service.deleteGoods(goodsId);
        System.out.println(result);
        JSONObject jsonObject = new JSONObject();
        if (result != 0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",400);
        }
        response.getWriter().print(jsonObject);
    }

    /**
     * 通过商品编号修改相应的商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateGoods(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String goodsForm =  request.getParameter("goodsForm");
        //JSONObject jsonObject = JSON.parseObject(goodsForm);
        Goods goods = JSON.parseObject(goodsForm,Goods.class);
        System.out.println(goods);
        int result = service.modifyGoods(goods);
        JSONObject object = new JSONObject();
        if (result != 0){
            object.put("status",200);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 添加新的商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addGoods(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String goodsForm = request.getParameter("goodsForm");
        Goods goods = JSON.parseObject(goodsForm,Goods.class);
        //System.out.println(goods);
        Goods goods1 = service.isGoodsExist(goods.getGoodsId());
        System.out.println(goods1);
        JSONObject jsonObject = new JSONObject();
        if (goods1 != null){
            jsonObject.put("status",100);  //证明要添加的商品已存在
        }else{
            int result = service.addNewGoods(goods);
            if (result != 0){
                jsonObject.put("status",200);
            }else {
                jsonObject.put("Status",400);
            }
        }
        response.getWriter().print(jsonObject);
    }

    public void getCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<Map<String,Object>> list = service.getCateList();
        JSONObject object = new JSONObject();
        object.put("cateList",list);
        response.getWriter().print(object);
    }

    /**
     * 上传或者修改图片
     * @param file
     * @param request
     * @param response
     */
    /*
    public void uploadLogo(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> map2 = new HashMap<>();
        String msg = "";
        String status = "";
        String params = request.getParameter("params");

        //接收处理参数
        Map map = JSONObject.toJavaObject(JSONObject.parseObject(params), Map.class);
        String id = map.get("id").toString();
        String logo = map.get("logo") == null ? "" : map.get("logo").toString();
        if (!"".equals(logo)) {//有则先删除原有图片
            String filePath = "E:/img"+ logo;//原有图片文件路径
            File oldFile = new File(filePath);
            if (oldFile.exists()) {
                try {
                    // 删除图片
                    oldFile.delete();
                } catch (Exception e) {
                    System.out.println("删除图片出错啦。。。");
                }
            } else {
                System.out.println("图片已经不存在了。。。");
            }
        }
        String fileName = file.getOriginalFilename();//文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//后缀名
        fileName = (UUID.randomUUID().toString()).replace("-", "") + suffixName;//上传保存的文件名
        File newFile = new File("E:/img/goods/" + fileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {

            //上传图片
            file.transferTo(newFile);
            Goods goods = new Goods();
            goods.setCateId(Integer.parseInt(id));
            goods.setGoodsLogo("/goods/" + fileName);
            int i = service.updateLogo(goods);
            status = "200";
            if (logo == "") {
                msg = "成功给添加了Logo图片信息！";
            } else {
                msg = "成功修改了的Logo图片信息！";
            }
        }catch (Exception e){
            System.out.println("图片上传失败。");
            status = "300";
            msg = "系统错误，图片上传失败。";
        }
        map2.put("status", status);
        map2.put("msg", msg);
        response.getWriter().print(map2);
    }*/





}
