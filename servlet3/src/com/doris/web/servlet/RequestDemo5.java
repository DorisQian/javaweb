package com.doris.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
/**
 * 获取请求体 一些通用方法，post中文乱码的解决
 */

@WebServlet("/requestDemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数通用方式 get和post都能用
        //根据参数名称获取参数值
//        System.out.println("post");

        //解决中文乱码　设置流的编码
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        System.out.println(username);
        //根据参数名称获取参数值的数组
        String[] hobby = request.getParameterValues("hobby");
        for (String ho : hobby) {
            System.out.println(ho);
        }
        //获取所有请求的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(name);
            String parameter = request.getParameter(name);
            System.out.println(parameter);
            System.out.println("-----------------------");
        }
        //获取所有参数的map集合
        System.out.println("-------map---------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keyset = parameterMap.keySet();
        for (String key : keyset) {
            String[] values = parameterMap.get(key);
            System.out.println(key);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("----------------");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数通用方式
        //根据参数名称获取参数值
//        System.out.println("get");
//        String username = request.getParameter("username");
//        System.out.println(username);
        this.doPost(request, response);
        //

    }
}
