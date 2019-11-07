package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RayTing
 * @date 2019-11-07 15:48
 * 测试session
 */
public class SessionTest extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("SessionTest->init()");
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SessionTest->doPost()");

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SessionTest->doGet()");

        // 如果不存在 session 会话，则创建一个 session 对象
        HttpSession session = request.getSession(true);

        // 获取 session 创建时间
        Date createTime = new Date(session.getCreationTime());

        // 获取该网页的最后一次访问时间
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        //设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String title = "Servlet Session 测试实例 旧" +session.isNew();
        Integer visitCount = 0;
        String visitCountKey = "visitCount";
        String userIDKey = "userID_Key";
        String userIDValue = "userID_Value";

        if (session.getAttribute(visitCountKey) == null) {
            session.setAttribute(visitCountKey,0);
        }


        // 检查网页上是否有新的访问者
        if (session.isNew()) {
            title = "Servlet Session 测试实例 isNew()";
            session.setAttribute(userIDKey, userIDValue);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userIDValue = (String) session.getAttribute(userIDKey);
        }

        session.setAttribute(visitCountKey, visitCount);

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String docType = "<!DOCTYPE html>";
        out.println(docType +
                "<html>" +
                "<head><title>" + title + "</title></head>" +
                "<body bgcolor='#f0f0f0'>" +
                "<h1 align='center'>" + title + "</h1>" +
                "<h2 align='center'>Session 信息</h2>" +
                "<table border='1' align='center'>" +
                "<tr bgcolor='#949494'>" +
                "  <th>Session 信息</th><th>值</th></tr>" +
                "<tr>" +
                "  <td>id</td>" +
                "  <td>" + session.getId() + "</td></tr>" +
                "<tr>" +
                "  <td>创建时间</td>" +
                "  <td>" + df.format(createTime) +
                "  </td></tr>" +
                "<tr>" +
                "  <td>最后访问时间</td>" +
                "  <td>" + df.format(lastAccessTime) +
                "  </td></tr>" +
                "<tr>" +
                "  <td>用户 ID</td>" +
                "  <td>" + userIDValue +
                "  </td></tr>" +
                "<tr>" +
                "  <td>访问统计：</td>" +
                "  <td>" + visitCount + "</td></tr>" +
                "</table>" +
                "</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("SessionTest->destroy()");
        super.destroy();
    }
}
