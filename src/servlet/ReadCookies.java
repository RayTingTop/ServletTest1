package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author RayTing
 * @date 2019-11-07 15:04
 * 查看保存的cookies
 */
public class ReadCookies extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("ReadCookies->init()");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;

        // 获取与该域相关的 Cookie 的数组
        cookies = request.getCookies();

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "Cookies 列表";
        String docType = "<!DOCTYPE html>";

        out.println(docType +"<html>" +"<head><title>" + title + "</title></head>" +"<body bgcolor='#f0f0f0'>" );
        if( cookies != null ){
            out.println("<h1 align='center'>" + title + "</h1>" +
                    "<table border='1' align='center'>" +
                    "<tr bgcolor='#949494'>" +
                    "<th>名称</th><th>值</th>" +
                    "</tr>");
            for (int i = 0; i < cookies.length; i++){
                cookie = cookies[i];
                if((cookie.getName()).compareTo("name") == 0 ){ //删掉名为name的cookie
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.print("<tr><td style='text-decoration:line-through'>已删除的 cookie：" + cookie.getName() + "</td>");
                    out.println("<td style='text-decoration:line-through'> " + URLDecoder.decode(cookie.getValue(), "utf-8") + "</td></tr>");
                }else {
                    out.print("<tr><td>" + cookie.getName() + "</td>");
                    out.println("<td> " + URLDecoder.decode(cookie.getValue(), "utf-8") + "</td></tr>");
                }
            }
            out.println("</table>");
        }else{
            out.println("<h2 class='tutheader'>No Cookie founds</h2>");
        }
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("ReadCookies->init()");
        super.destroy();
    }
}
