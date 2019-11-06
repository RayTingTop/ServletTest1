import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author RayTing
 * @date 2019-11-06 16:35
 */
@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // 执行必需的初始化
    public void init() throws ServletException {
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    // 什么也不做
    public void destroy() {

    }
}
