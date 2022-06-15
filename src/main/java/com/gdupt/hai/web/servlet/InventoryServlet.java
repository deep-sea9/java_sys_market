package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Inventory;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.service.InventoryService;
import com.gdupt.hai.service.impl.InventoryServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventoryServlet")
public class InventoryServlet extends BaseServlet {

    InventoryService service = new InventoryServiceImpl();
    /**
     * 获取库存列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryAllInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        Pager<Inventory> pager = service.getInventory(page,size);
        JSONObject object = new JSONObject();
        object.put("inventoryList",pager);
        if (pager != null){
            object.put("status",200);
        }
        else {
            object.put("status",400);
        }
        System.out.println(pager);
        response.getWriter().print(object);
    }

    public void getInventoryList(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String queryForm = request.getParameter("query");
        Inventory inventory = JSON.parseObject(queryForm,Inventory.class);
        List<Inventory> list = service.queryInventory(inventory.getGoodsName(),inventory.getGoodsId());
        System.out.println(list);
        JSONObject object = new JSONObject();
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
            object.put("inventoryList",list);
        }
        response.getWriter().print(object);

    }

    public void removeInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String goodsId = request.getParameter("goodsId");
        System.out.println(goodsId);

        JSONObject object = new JSONObject();
        int result = service.removeInventory(goodsId);
        if (result != 0){
            object.put("status",200);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 进货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inventoryForm = request.getParameter("inventoryForm");
        int total = Integer.parseInt(request.getParameter("total"));
        Stock stock = JSON.parseObject(inventoryForm,Stock.class);
        JSONObject object = new JSONObject();
        int result = service.addInventory(stock);
        System.out.println("result"+result);
        if (result != 0){
            service.updateInventory(stock.getDate(),total,stock.getGoodsId());
            object.put("status",200);
        }else{
            object.put("status",400);
        }
        response.getWriter().print(object);

    }

    /**
     * 添加库存
     * @param request
     * @param response
     * @throws Exception
     */
    public void addNewInventory(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String form = request.getParameter("form");
        Inventory inventory = JSON.parseObject(form,Inventory.class);
        JSONObject object = new JSONObject();
        List<Inventory> list = service.exitInventory(inventory.getGoodsId());
        if (list.isEmpty()){
            int result = service.increaseInventory(inventory);
            if (result != 0){
                object.put("status",200);
            }else{
                object.put("status",400);
            }
        }else{
            object.put("status",100);  //商品库存已创建
        }
        response.getWriter().print(object);

    }

}
