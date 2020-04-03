package jeecg.Client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/Hello")
public class ClientController {
    //测试HttpServletRequest 对象代表客户端的请求,当客户端通过http协议请求访问
    // HttpServletResponse 响应客户端
    @RequestMapping("/add")
    public void addrest( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
     //  request.getRequestDispatcher("/index.html").forward(request, response);
        PrintWriter out = response.getWriter();

            //客户端发出请求时的完整URL
        StringBuffer URL = request.getRequestURL();
        System.out.println("完整URL："+URL.toString());
        out.write("完整URL："+URL.toString());
        //客户端请求uri
        String uri = request.getRequestURI();
        System.out.println("客户端请求uri："+uri);
        //客户端请求行中的参数部分
        String queryString = request.getQueryString();
        System.out.println("传递的参数："+queryString);
        //返回请求的方案名，如http,ftp,https等
        String scheme = request.getScheme();
        System.out.println("请求的方案名:"+scheme);
        //HTTP请求的的方法名，默认是GET，也可以指定PUT或POST
        String method = request.getMethod();
        System.out.println("请求方法："+method);
        //返回url的额外路径信息
        String pathInfo = request.getPathInfo();
        System.out.println("url的额外路径信息"+pathInfo);
        //返回一个表示在服务器文件系统上的PathInfol转换成路径的字符串
        String pathTranslated = request.getPathTranslated();//返回null
        System.out.println("服务器文件系统上的PathInfol转换成路径的字符串:"+pathTranslated);
//放回请求体的协议名和版本
        String protocol = request.getProtocol();
        System.out.println("请求体的协议名和版本:"+protocol);
        //工程之后到参数之前的这部分字符串
        String servletPath = request.getServletPath();
        System.out.println("工程之后到参数之前的这部分字符串："+servletPath);
        //客户端ip
        String ip = request.getRemoteAddr();
        System.out.println("客户端ip"+ip);
        //客户端完整主机名
        String host = request.getRemoteHost();
        System.out.println("主机名"+host);
        //客户端端口
        int port = request.getRemotePort();
        System.out.println("客户端端口："+port);
        //获取web服务器的主机和端口,主机名
        String webHost = request.getLocalAddr();
        int webPort = request.getLocalPort();
        String webName = request.getLocalName();
        System.out.println("web服务主机："+webHost+"端口："+webPort+"主机名："+webName);
        //获取请求头信息
        Enumeration<String> headers = request.getHeaderNames();
        System.out.println("所有请求头信息");
        //boolean hasMoreElements()
        //          测试此枚举是否包含更多的元素。
        while(headers.hasMoreElements()){
            //返回此枚举的下一个元素。
            String headName = (String)headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println(headName+"："+headValue);
        }
        //获取客户端请求参数
        //根据请求头获取参数信息
        String school = request.getHeader("School");
        Enumeration<String> shouji = request.getHeaders("shouji");
        String school1 = request.getParameter("School");
        System.out.println("ssd"+school1);
        //request对象封装的参数是以Map的形式存储的
        Map<String, String[]> paramMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry :paramMap.entrySet()){
            String paramName = entry.getKey();
            String paramValue = "";
            //对参数赋值
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr!=null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length-1) {
                    paramValue+=paramValueArr[i];
                }else {
                    paramValue+=paramValueArr[i]+",";
                }
            }
            //和日期一样的格式化输出
            System.out.println(MessageFormat.format("{0}={1}", paramName,paramValue));
        }
        //调用响应方法
        new ResponeClient().RES(request,response);
    }

}
