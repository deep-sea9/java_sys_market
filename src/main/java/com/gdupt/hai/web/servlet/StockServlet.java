package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Inventory;
import com.gdupt.hai.domain.Stock;
import com.gdupt.hai.service.StockService;
import com.gdupt.hai.service.impl.StockServiceImpl;
import com.gdupt.hai.utils.ExcelUtils;
import com.gdupt.hai.web.baseServlet.BaseServlet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StockServlet")
public class StockServlet extends BaseServlet {

    StockService service = new StockServiceImpl();

    public void getStockList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject object = new JSONObject();
        List<Stock> list = service.getStock();
        object.put("stockList",list);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        response.getWriter().print(object);
    }

    public void getDateList(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String date = request.getParameter("date");
        JSONObject object = new JSONObject();
        List<Stock> list = service.selectDate(date);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        object.put("DateList",list);
        response.getWriter().print(object);
    }



    public void selectYear(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String year = request.getParameter("year");
        System.out.println("======="+year);
        JSONObject object = new JSONObject();
        List<Stock> list = service.selectYear(year);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        object.put("yearList",list);
        response.getWriter().print(object);

    }

    public void selectMonth(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String month = request.getParameter("month");
        String[] str = month.split("-");
        JSONObject object = new JSONObject();
        List<Stock> list = service.selectMonth(str[0],str[1]);
        if (list.isEmpty()){
            object.put("status",400);
        }else{
            object.put("status",200);
        }
        object.put("monthList",list);
        response.getWriter().print(object);

    }

    public void selectDateRange(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");
        JSONObject object = new JSONObject();
        List<Stock> list = service.selectDateRange(start,end);
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        object.put("RangeList",list);
        response.getWriter().print(object);
    }

    /**
     *
     * @param request
     * @param response 导出库存记录
     * @throws ServletException
     * @throws IOException
     */
    public void exportStockExcel(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        // 标题
        String[] title = {"编号", "进货单号", "商品编号", "商品名称", "供应商","单价","数量","进货日期","进货时间"};
        // sheet名
        String sheetName = "进货信息";
        //获取时间，可以按时间查询
        String date = request.getParameter("date");
        List<Stock> stock = null;
        //按日期去查
        if (date==null){//为空，则全查询
            stock = service.getStock();
        } else {//否，就按data查询
            stock = service.selectDate(date);
        }
        String[][] content = new String[stock.size()][9];
        for (int i = 0; i < stock.size(); i++) {
            content[i] = new String[title.length];
            Stock stock1 = stock.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = stock1.getImportBill();//进货单号
            content[i][2] = stock1.getGoodsId();//商品编号
            content[i][3] = stock1.getGoodsName();//商品名称
            content[i][4] = stock1.getProffer();//供应商
            content[i][5] = String.valueOf(stock1.getPrice());//单价
            content[i][6] = String.valueOf(stock1.getQuantity());//数量
            content[i][7] = String.valueOf(stock1.getTotal());//总额
            content[i][8] = String.valueOf(stock1.getDate());//时间
        }
        // 创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);
        //输出
        try {
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
