package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Goods;
import com.gdupt.hai.service.GoodsService;
import com.gdupt.hai.service.impl.GoodsServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "uploadServlet")
public class uploadServlet extends BaseServlet {

    /**
     * 下载
     */
    public void download(HttpServletRequest request,HttpServletResponse response){

        String type = request.getParameter("type");
        System.out.println("type======"+type);
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            String fileName  = "离职申请表.docx";
            System.out.println("文件为"+fileName);
            //获取文件的路径
            //String filePath = "E:/download" + "/" + fileName;//"E:/img"
            String filePath = "/home/excel" + "/" + fileName;//"E:/img"
            fileInputStream = new FileInputStream(filePath);
            outputStream = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            System.out.println("模板下载成功！");
        } catch (Exception e) {
            System.out.println("出现异常，模板下载失败。");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downloadExcel(HttpServletRequest request,HttpServletResponse response){
        try {
            //获取要下载的模板名称
            String fileName = "goods.xlsx";
            //设置要下载的文件的名称
            response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
            //通知客服文件的MIME类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //获取文件的路径
            String filePath = getClass().getResource("/template/" + fileName).getPath();
            System.out.println(filePath);
            FileInputStream input = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
            response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
            //return response.ok("应用导入模板下载完成");
            System.out.println("应用导入模板下载成功！");
        } catch (Exception ex) {
            //log.error("getApplicationTemplate :", ex);
            System.out.println(ex);
            // return response.ok("应用导入模板下载失败！");
            System.out.println("应用导入模板下载失败！");
        }
    }

    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        Goods goods = JSON.parseObject(data,Goods.class);
        JSONObject object = new JSONObject();
        System.out.println(goods);
        GoodsService service = new GoodsServiceImpl();
        Goods goods1 = service.isGoodsExist(goods.getGoodsId());
        if (goods1 != null){
            object.put("status",100);
        }else{
            int result = service.addNewGoods(goods);
            if (result != 0){
                object.put("status",200);
            }else {
                object.put("status", 400);
            }
        }

        response.getWriter().print(object);
    }
}
