package com.doris.web.servlet.response;

import com.doris.utils.DownloadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class Download extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String filename = request.getParameter("filename");
        //使用字节输入流加载文件到内存
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);
        FileInputStream fileInputStream = new FileInputStream(realPath);

        //设置response响应头
        String mimeType = context.getMimeType(filename);
        response.setContentType(mimeType);

        //解决中文乱码
        String agent = request.getHeader("user-agent");
        filename = DownloadUtils.getFileName(agent, filename);

        response.setHeader("content-disposition", "attachment;filename=" + filename);
        //将输入流写到输出流
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
        }
        fileInputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
