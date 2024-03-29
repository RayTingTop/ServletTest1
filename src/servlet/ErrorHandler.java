package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author RayTing
 * @date 2019-11-07 11:44
 * servlet异常处理
 */
public class ErrorHandler extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("ErrorHandler->init()");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--ErrorHandler->doPost()");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ErrorHandler->doGet()");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = " Error/Exception 信息";

        String docType = "<!DOCTYPE html>";
        out.println(docType + "<html>" + "<head><title>" + title + "</title></head>" + "<body bgcolor='#f0f0f0'>");
        out.println("<h1>异常信息</h1>");

        if (throwable == null && statusCode == null) {
            out.println("<h2>错误信息丢失</h2>");
            out.println("请返回 <a href='index.jsp'>主页</a>。");
        } else if (statusCode != null) {
            out.println("错误代码 : " + statusCode);
        } else {
            out.println("<h2>错误信息</h2>");
            out.println("Servlet Name : " + servletName + "</br></br>");
            out.println("异常类型 : " + throwable.getClass().getName() + "</br></br>");
            out.println("请求 URI: " + requestUri + "<br><br>");
            out.println("异常信息: " + throwable.getMessage());
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("ErrorHandler->destroy()");
        super.destroy();
    }
}
