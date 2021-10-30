package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo resultInfo = new ResultInfo();
        //将info对象序列化成json
        ObjectMapper mapper = new ObjectMapper();

        //验证码校验
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String check = (String) session.getAttribute("CHECKOUT_SERVER");
        session.removeAttribute("CHECKOUT_SERVER");
        if (checkCode == null || ! checkCode.equalsIgnoreCase(check)){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(resultInfo);
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
        UserServiceImpl userService = new UserServiceImpl();
        Boolean flag = userService.register(user);

        //响应结果
        if (flag) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }

        String json = mapper.writeValueAsString(resultInfo);
        //将json写回客户端
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
