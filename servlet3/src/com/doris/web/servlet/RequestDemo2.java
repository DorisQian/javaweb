package com.doris.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 演示Request对象获取请求头数据
 */
@WebServlet("/requestDemo2")
public class RequestDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //遍历
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println(header);
        }
    }
}
