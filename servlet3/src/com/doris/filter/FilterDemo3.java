package com.doris.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 访问资源
 */
//@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST)
@WebFilter(value = "/*", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class FilterDemo3 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //对request消息增强
        System.out.println("demo3");
        //放行
        chain.doFilter(req, resp);
        //对response对象的响应消息增强
        System.out.println("demo3回来了");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
