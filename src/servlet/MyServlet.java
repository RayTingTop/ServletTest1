package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author RayTing
 * @date 2019-11-06 16:37
 * 接收表单信息，定时刷新时间，显示请求头
 */
@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException {
        System.out.println("MyServlet->init()");
        message = "This is MyServlet, CurrentTime: ";
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyServlet—>doGet()");

        response.setIntHeader("Refresh", 5); // 设置刷新自动加载时间为 5 秒
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>" + message + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "</h1>");

        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        if (!name.equals("张三")){
            throw new ServletException(); //抛出异常
        }

        writer.write("<h1>" + name + "</h1>");


        String docType = "<!DOCTYPE html> ";
        String title ="请求头信息";
        writer.println(docType +
                "<html>" +
                "<head><meta charset='utf-8'><title>" + title + "</title></head>"+
                "<body bgcolor='#f0f0f0'>" +
                "<h1 align='center'>" + title + "</h1>" +
                "<table width='100%' border='1' align='center'>" +
                "<tr bgcolor='#949494'>" +
                "<th>Header 名称</th><th>Header 值</th>"+
                "</tr>");

        Enumeration headerNames = request.getHeaderNames(); //获取所有的头名

        while(headerNames.hasMoreElements()) { //遍历请求头
            String paramName = (String)headerNames.nextElement();
            writer.print("<tr><td>" + paramName + "</td>");
            String paramValue = request.getHeader(paramName);//获取请求头信息
            writer.println("<td> " + paramValue + "</td></tr>");
        }
        writer.println("</table></body></html>");

        destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--MyServlet—>doPost()");
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("MyServlet—>destroy()");
        super.destroy();
    }
}
