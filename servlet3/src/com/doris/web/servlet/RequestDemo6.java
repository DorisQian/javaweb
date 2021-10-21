package com.doris.web.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发，服务器内部的资源跳转方式
 * 只能跳转服务器内部的地址
 * 转发是一次请求
 */
@WebServlet("/requestDemo6")
public class RequestDemo6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo6被访问");
        //转到demo7资源
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requestDemo7");
//        requestDispatcher.forward(request, response);
        request.getRequestDispatcher("/requestDemo7").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
