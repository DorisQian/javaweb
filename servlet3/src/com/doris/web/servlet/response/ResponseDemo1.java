package com.doris.web.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 重定向
 * 设置相应行，响应头，响应体
 *
 */

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo1");
//        response.setStatus(302);
//        response.setHeader("location", "/responseDemo2");
//        response.sendRedirect("/servlet3/responseDemo2");
        //动态获取虚拟目录
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/responseDemo2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
