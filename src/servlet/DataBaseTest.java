package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author RayTing
 * @date 2019-11-07 16:10
 * servlet 访问数据库
 */
public class DataBaseTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL，数据库的用户名与密码
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASSWORD = "Y602h5f203a.";

    @Override
    public void init() throws ServletException {
        System.out.println("DataBaseTest->init()");
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DataBaseTest->doPost()");
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DataBaseTest->doGet()");
        Connection conn = null;
        PreparedStatement pstmt = null;
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet Mysql 测试";
        String docType = "<!DOCTYPE html>";
        out.println(docType +
                "<html>" +
                "<head><title>" + title + "</title></head>" +
                "<body bgcolor='#f0f0f0'>" +
                "<h1 align='center'>" + title + "</h1>");
        try {
            // 注册 JDBC 驱动器
            Class.forName(DRIVER);

            // 打开一个连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 执行 SQL 查询
            pstmt = conn.prepareStatement("SELECT * FROM employee");

            ResultSet rs = pstmt.executeQuery();


            out.println("<table border='1' align='center'>" +
                    "<tr bgcolor='#949494'>" +
                    "<th>ID</th><th>姓名</th><th>性别</th><th>密码</th>" +
                    "</tr>");

            // 展开结果集数据库
            while (rs.next()) {

                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String password = rs.getString("password");

                // 输出数据
                out.print("<tr>");
                out.print("<td>" + id + "</td>");
                out.print("<td>" + name + "</td>");
                out.print("<td>" + gender + "</td>");
                out.print("<td>" + password + "</td>");
                out.print("</tr>");
            }
            out.println("</table>");
            // 完成后关闭
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("DataBaseTest->destroy()");

        super.destroy();
    }
}

