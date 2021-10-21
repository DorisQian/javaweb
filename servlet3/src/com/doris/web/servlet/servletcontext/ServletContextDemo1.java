package com.doris.web.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * ServletContext 代表整个web应用，可以和程序的容器（服务器）来通信
 * 获取对象
 * MIME类型 ey: text/html
 * 域对象 ServletContext可以共享所有数据的对象，谨慎应用，servlet启用就存在，服务关闭才销毁
 * 获取文件的真实（服务器）路径
 */
@WebServlet("/servletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //servletcontext对象的获取
        //1 request
        ServletContext servletContext = request.getServletContext();
        // httpservlet
        ServletContext context = this.getServletContext();
        System.out.println(servletContext);
        System.out.println(context);

        //获取mine类型
        String filename = "a.jpg";
        String mimeType = context.getMimeType(filename);
        System.out.println(mimeType);

        //域对象
        //不需要跳转，ServletContext可以共享所有数据的对象，谨慎应用，servlet启用就存在，服务关闭才销毁
        context.setAttribute("msg", "hello");

        //获取真实路径
        String path = context.getRealPath("/post.html"); //web目录下资源访问
        System.out.println(path);
        File file = new File(path);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
