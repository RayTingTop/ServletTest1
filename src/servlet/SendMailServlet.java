package servlet;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author RayTing
 * @date 2019-11-12 13:17
 * 发送邮件servlet
 */
public class SendMailServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("SendMailServlet->init()");
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SendMailServlet->doPost()");
        doGet(request,response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SendMailServlet->doGet()");

        String from = request.getParameter("from"); // 发件人的电子邮件 ID
        String to = request.getParameter("to"); //收件人的电子邮件 ID
        String token = request.getParameter("token");  //smtp授权码
        String mailTitle = new String(request.getParameter("mailTitle").getBytes("ISO-8859-1"), "UTF-8"); //消息内容
        String mailMessage = new String(request.getParameter("mailMessage").getBytes("ISO-8859-1"), "UTF-8"); //消息内容

        System.out.println("发:"+from);
        System.out.println("收:"+to);
        System.out.println("码:"+token);
        System.out.println("息:"+mailMessage);

        // 指定发送邮件的主机为 smtp.qq.com，使用qq服务器
        String host = "smtp.qq.com";

        // 获取系统的属性
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host); // 设置邮件服务器

        properties.setProperty("mail.smtp.auth", "true");//开启认证
        properties.setProperty("mail.debug", "true");//启用调试
        properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
        properties.setProperty("mail.smtp.port", "465");//设置端口
        properties.setProperty("mail.smtp.socketFactory.port", "465");//设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        SmtpAuthenticator authentication = new SmtpAuthenticator();
        authentication.setUsername(from);
        authentication.setPassword(token); //这里是邮箱服务器的smtp授权码

        // 获取默认的 Session 对象
        Session session = Session.getDefaultInstance(properties,authentication);

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //ServletContext 把它的消息记录到 Servlet 容器的日志文件中。Tomcat在 <Tomcat-installation-directory>/logs 中
        ServletContext context = getServletContext();
        try{

            MimeMessage message = new MimeMessage(session); // 创建一个默认的 MimeMessage 对象

            message.setFrom(new InternetAddress(from));// 设置 From: header field of the header.

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 设置 To: header field of the header.

            message.setSubject(mailTitle); // 设置邮件标题
            message.setText(mailMessage); // 现在设置实际消息

            Transport.send(message); // 发送消息

            String title = "发送电子邮件";
            String res = "成功发送消息...";
            String docType = "<!DOCTYPE html> ";

            System.out.println(res);
            System.out.println("发送内容："+mailMessage);

            out.println(docType +
                    "<html>" +
                    "<head><title>" + title + "</title></head>" +
                    "<body bgcolor='#f0f0f0'>" +
                    "<h1 align='center'>" + title + "</h1>" +
                    "<p align='center'>" + res + "</p>" +
                    "</body></html>");
        }catch (MessagingException mex) {
            context.log("邮件发送异常");//使出日志
            mex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("SendMailServlet->init()");
        super.destroy();
    }

}
