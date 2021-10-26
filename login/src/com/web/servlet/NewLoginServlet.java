package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/newLoginServlet")
public class NewLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取参数map
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //先判断验证码是否正确
        HttpSession session = request.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCodeSession");
        //删除session存储的验证码,避免页面后退，验证码不变
        session.removeAttribute("checkCodeSession");
        if (checkCodeSession!= null && checkCodeSession.equalsIgnoreCase(checkCode)){
            //忽略大小写
            if ("zhangsan".equals(username) && "123".equals(password)){ //先写死
                //存储用户信息
                session.setAttribute("user", username);
                //重定向到success.jsp
                response.sendRedirect("success.jsp");
            } else{
                request.setAttribute("login_error", "用户名密码错误");
                //转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("check_error", "验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
