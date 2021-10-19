package servlet;

import javax.servlet.*;
import java.io.IOException;

public class ServletDemo2 implements Servlet {
    /**
     * 在创建servlet时执行，只一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("services init。。。。");
    }

    /**
     * 返回servlet配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务方法
     * 每一次servlet被访问就执行一次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet");
    }

    /**
     * 获取serlvet信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 服务器正常关闭时，执行，只一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
