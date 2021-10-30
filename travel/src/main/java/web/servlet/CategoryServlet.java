package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = categoryService.findAll();
        writeValue(cs, response);
    }

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cate.find");
    }
}
