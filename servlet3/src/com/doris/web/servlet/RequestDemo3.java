package com.doris.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo3")
public class RequestDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求头数据 user-agent
        String agent = request.getHeader("user-agent");
        //判断agent浏览器版本
        if (agent.contains("Chrome")){
            System.out.println("chrome");
        }else if(agent.contains("Firefox")){
            System.out.println("firefox");
        }

        //referer
        String referer = request.getHeader("referer");
        System.out.println(referer);

        //防盗链
        if (referer != null){
            if (referer.contains("/servlet3")){
                System.out.println("正常");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("正常访问");
            }else{
                System.out.println("盗链");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("盗链");
            }
        }
    }
}
