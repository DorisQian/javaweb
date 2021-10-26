package com.doris.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多个cookie 默认关闭浏览器就失效，可以设置 如下
 * setMaxAge(int ) 设置cookie时间
 *      正数，存在硬盘，设置的时间后会自动删除
 *      负数，就是走浏览器默认，关闭则删除
 *      0，删除cookie
 * tomcat 8之后支持中文
 * cookie的取值范围
 *      多个web项目，cookie默认不能共享
 *      设置cookie共享范围
 *      setPath(String path)  path设置成虚拟目录来限制，可以设置成/,则项目下都可以共享
 */
@WebServlet("/cookieDemo3")
public class CookieDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建cookie对象
        Cookie cookie1 = new Cookie("msg", "hello");
        Cookie cookie2 = new Cookie("name", "zhangsan");
        Cookie cookie3 = new Cookie("name", "你好");

        //设置cookie的存活时间 30s
        cookie1.setMaxAge(30);
        //发送cookie
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
