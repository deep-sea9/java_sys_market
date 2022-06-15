package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.domain.Proffer;
import com.gdupt.hai.service.ProfferService;
import com.gdupt.hai.service.impl.ProfferServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfferServlet")
public class ProfferServlet extends BaseServlet {

    ProfferService service = new ProfferServiceImpl();

    /**
     * 获取供应商列表，并分页显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getProffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject object = new JSONObject();

        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        Pager<Proffer> pager = service.getAllSupply(page,size);
        if (pager != null) {
            object.put("status", 200);
            object.put("profferList", pager);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 添加供应商信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void addProffer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String profferForm = request.getParameter("supplyForm");
        Proffer proffer = JSON.parseObject(profferForm,Proffer.class);
        JSONObject object = new JSONObject();
        Proffer proffer1 = service.exitProffer(proffer.getProfferId(), proffer.getFullName());
        if (proffer1 != null){  //该供应商已存在
            object.put("status",100);
        }else {
            int result = service.addNewProffer(proffer);
            System.out.println("Result="+result);
            if (result != 0){
                object.put("status",200);   //添加成功
            }else {
                object.put("Status",400);   //添加失败
            }
        }
        response.getWriter().print(object);
    }

    /**
     * 修改供应商信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void updateProffer(HttpServletRequest request,HttpServletResponse response) throws Exception{
        JSONObject object = new JSONObject();
        String profferForm = request.getParameter("supplyForm");
        Proffer proffer = JSON.parseObject(profferForm,Proffer.class);
        int result = service.editProffer(proffer);
        if (result != 0){
            object.put("status",200);
        }else{
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 删除供应商
     * @param request
     * @param response
     * @throws Exception
     */
    public void deleteProffer(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String profferId = request.getParameter("profferId");
        JSONObject object = new JSONObject();
        int result = service.removeProffer(profferId);
        if (result != 0){
            object.put("status",200);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 查询供应商
     * @param request
     * @param response
     * @throws Exception
     */
    public void findSupply(HttpServletRequest request, HttpServletResponse response) throws Exception{
        JSONObject object = new JSONObject();
        String profferName = request.getParameter("profferName");
        List<Proffer> profferList = service.getProfferByName(profferName);
        if (profferList.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
        }
        object.put("profferList",profferList);
        response.getWriter().print(object);
    }

}
