package com.doris.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 *  * 记住上一次访问时间,判断是不是第一次访问
 */
@WebServlet("/CookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    flag = true;
                    //获取当前时间字符串，重新设置cookie并发送
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String format = simpleDateFormat.format(date);
                    cookie.setValue(URLEncoder.encode(format, "utf-8"));
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);

                    String value = cookie.getValue();
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上一次访问时间为：" + value + "</h1>");
                    break;
                }
            }
        }

        if (cookies == null || cookies.length == 0 || flag == false){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String format = simpleDateFormat.format(date);
            format = URLEncoder.encode(format, "utf-8");
            Cookie cookie = new Cookie("lastTime", format);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.getWriter().write("<h1>欢迎您首次访问</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
