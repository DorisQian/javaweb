package com.doris.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 监听器
 * 配置 xml <listener><listener-class>全类名</listener-class></listener>
 * 指定初始化参数 <context>
 *     <param-name>name</param-name>
 *     <param-value>文件路径</param-value>
 * </context>
 * 注解 WebListener
 */
//@WebListener
public class ListenerDemo1 implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //服务器启动后自动调用
        //加载资源文件
        ServletContext servletContext = sce.getServletContext();
        String initParameter = servletContext.getInitParameter("name");//加载配置文件，根据xml配置
        String realPath = servletContext.getRealPath(initParameter);
        try {
            FileInputStream fileInputStream = new FileInputStream(realPath);
            System.out.println(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("listener对象被创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //服务器正常关闭后调用
        System.out.println("listener对象销毁");
    }
}
