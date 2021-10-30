package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo resultInfo = new ResultInfo();

        //验证码校验
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String check = (String) session.getAttribute("CHECKOUT_SERVER");
        session.removeAttribute("CHECKOUT_SERVER");
        if (checkCode == null || ! checkCode.equalsIgnoreCase(check)){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = writeValueAsString(resultInfo);
            //将json写回客户端
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册
        Boolean flag = userService.register(user);

        //响应结果
        if (flag) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }

        String json = writeValueAsString(resultInfo);
        //将json写回客户端
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String code = request.getParameter("code");
        if (code != null) {
            Boolean flag = userService.active(code);
            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User u = userService.login(user);

        ResultInfo info = new ResultInfo();
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名密码错误");
        }else if(!"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }else{
            request.getSession().setAttribute("user", u);
            info.setFlag(true);
        }
        writeValue(info, response);
    }

    /**
     * 查找一个用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object user = request.getSession().getAttribute("user");
        writeValue(user, response);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
