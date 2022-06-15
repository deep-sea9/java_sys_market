package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Sale;
import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.service.SaleService;
import com.gdupt.hai.service.StockService;
import com.gdupt.hai.service.impl.SaleServiceImpl;
import com.gdupt.hai.service.impl.StockServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;
import net.sf.jsqlparser.expression.JsonExpression;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SaleServlet")
public class SaleServlet extends BaseServlet {

    SaleService service = new SaleServiceImpl();

    public void getAllSales(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        System.out.println(page + " " +size);
       //  service = new SaleServiceImpl();
        Pager<Sale> pager = service.getSales(page,size);
        JSONObject object = new JSONObject();
        if (pager != null){
            object.put("status",200);
            object.put("saleList",pager);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 根据商品名称查找销售记录
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSaleByName(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        String goodsName = request.getParameter("goodsName");
        JSONObject object = new JSONObject();
        List<Sale> list = service.getSaleByName(goodsName);
        object.put("list",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        response.getWriter().print(object);
    }

    public void getDateList (HttpServletRequest request, HttpServletResponse response) throws Exception{
        String date = request.getParameter("date");
        JSONObject object = new JSONObject();
        List<Sale> list = service.findSaleDate(date);
        object.put("list",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);

        }
        response.getWriter().print(object);
    }

    public void selectYear (HttpServletRequest request, HttpServletResponse response) throws Exception{
        String year = request.getParameter("year");
        JSONObject object = new JSONObject();
        List<Sale> list = service.findSaleYear(year);
        object.put("list",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);

        }
        response.getWriter().print(object);

    }

    public void selectMonth (HttpServletRequest request, HttpServletResponse response) throws Exception{
        String month = request.getParameter("month");
        System.out.println("++++++++"+month);
        JSONObject object = new JSONObject();
        String[] str = month.split("-");
        List<Sale> list = service.findSaleMonth(str[0],str[1]);
        object.put("list",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        response.getWriter().print(object);
    }

    public void selectDateRange (HttpServletRequest request, HttpServletResponse response) throws Exception{
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");
        JSONObject object = new JSONObject();
        List<Sale> list = service.findSaleRange(start,end);
        object.put("list",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);

        }
        response.getWriter().print(object);
    }

    /**
     * 统计销量top10，类目销量
     * @param request
     * @param response
     * @throws ServletException
     * @throws Exception
     */
    public void statisticsReport(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String date = request.getParameter("date");//获取时间,可以为空
       // SaleService service = new SaleServiceImpl();
        StockService service1 = new StockServiceImpl();
        List<Map<String,Object>> list = service.findSales(date);
        List<Map<String,Object>> listCate = service.findCate();
        List<Map<String,Object>> listSales = service.findSaleSevenDay();
        List<Map<String, Object>> listInput = service1.findSupplyInput();
        System.out.println("list是"+list);
        JSONObject object = new JSONObject();
        if (list.size()>0){
            object.put("status",200);
            object.put("list",list);
            object.put("listCate",listCate);
            object.put("listSales",listSales);
            object.put("listInput",listInput);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 统计各个阶段的销售额
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSaleDate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        JSONObject object = new JSONObject();
        Map<String,Double> map = service.getSalesDate();
        List<Map<String,Object>> list = service.getSalesMonth();
        object.put("monthSales",list);
        object.put("salesDate",map);
        response.getWriter().print(object);
    }

    /**
     * 获取首页数据统计：当天销售额，当天进货量，过去七天销量
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSalesStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception{
        JSONObject object = new JSONObject();
        List<Map<String,Object>> list = service.findSaleSevenDay();
        double salesToday = service.getSalesToday();
        StockService stockService = new StockServiceImpl();
        double importSales = stockService.getInputToday();
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
            object.put("list",list);
            object.put("salesToday",salesToday);
            object.put("importSales",importSales);
        }
        response.getWriter().print(object);
    }

    /**
     * 统计近七天的供应商供货情况
     * @param request
     * @param response
     * @throws Exception
     */
    public void findSupplyInput(HttpServletRequest request,HttpServletResponse response) throws Exception{
        JSONObject object = new JSONObject();
        StockService service = new StockServiceImpl();
        List<Map<String, Object>> list = service.findSupplyInput();
        if (list.isEmpty()){
            object.put("status",400);
        }else{
            object.put("status",200);
        }
        object.put("inputList",list);
        response.getWriter().print(object);
    }

}
