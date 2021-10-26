package com.doris.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 存储一次回话多次请求的数据，可以存储任意类型，任意大小的数据
 * 使用session共享数据
 * 获取session,设置数据
 * 基于cookie实现的，想要客户端关闭后，session用同一个，即存在多个对话间，设置cookie
 * 客户端不关闭，服务器关闭，session也不是同一个，
      要确保数据不丢失 session钝化和活化，tomcat自动完成，idea不能实现
      钝化：在服务器正常关闭前，将session对象系列化到硬盘
      活化：在服务器启动后，将session文件转化为内存中的session对象
 * session销毁
 * 服务器关闭
 * session调用invalidate()
 * 自动失效时间 30分钟 可配置，tomcat session-config
 */
@WebServlet("/sessionDemo1")
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie c = new Cookie("JESSIONID", session.getId());
        c.setMaxAge(60 * 60);
        response.addCookie(c);
        session.setAttribute("msg", "hello session");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
