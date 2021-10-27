package com.doris.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * 配置拦截路径：具体资源路径、拦截目录 /usr/*、拦截后缀名 *.jsp、拦截所有 /*
 * @WebFilter("/*")
 * web.xml中配置，见web模块中的xml
 *
 * 拦截方式：资源被访问的方式
 * 注解:dispatcherTypes
 *      REQUEST 默认，浏览器直接请求资源
 *      FORWARD：转发访问
 *      INCLUDE：包含访问
 *      ERROR：错误跳转
 *      ASYNC：异步访问
 * web.xml:<dispatcher>FORWARD</dispatcher>
 *
 * 过滤器链
 * 顺序：过滤器1，过滤器2，资源，过滤器2，过滤器1
 * 过滤器执行顺序，注解 按照类名排序 xml，定义顺序<filter-mapping></filter-mapping>配置顺序
 */
@WebFilter("/*")//访问所有资源之前都会执行该过滤器
public class FilterDemo1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //服务器启动后创建fliter对象，调用init方法
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filterDemo1被执行了。。。");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //服务器关闭后，filter对象被销毁，正常关闭执行destroy
    }
}
