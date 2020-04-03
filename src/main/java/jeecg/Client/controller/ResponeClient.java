package jeecg.Client.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponeClient {
    public  void  RES(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletInputStream inputStream = request.getInputStream();
        //设置Content-Type响应头
        response.setHeader("Content-Type","text/html;charset=utf-8");
//添加一个响应头：xxx是表示某一响应头
        response.addHeader("xxx", "123");
//通知客户端响应内容长度为888个字节
        response.setIntHeader("Context-Length", 5);
//设置过期时间为:5000毫秒
        response.setDateHeader("postterm_time", 5000);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("测试响应信息");
        response.sendRedirect(request.getContextPath() + "/index.html");


    }
}
