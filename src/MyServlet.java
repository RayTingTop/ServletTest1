import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RayTing
 * @date 2019-11-06 16:37
 */
@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException {
        message = "This is MyServlet, CurrentTime: ";
        System.out.println("servlet初始化……");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet——doGet！");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>" + message + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "</h1>");
        destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet——doPost！");
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁!\n");
        super.destroy();
    }
}
