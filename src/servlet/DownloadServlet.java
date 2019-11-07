package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author RayTing
 * @date 2019-11-07 17:13
 * 文件下载servlet
 */
public class DownloadServlet extends HttpServlet {
    // 执行必需的初始化
    public void init() throws ServletException {
        System.out.println("DownloadServlet->doGet()");
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DownloadServlet->doGet()");
        //获取文件名
        String filename=new String(request.getParameter("fileName").getBytes("iso8859-1"),"utf-8");

        //在控制台打印文件名
        System.out.println("文件名："+filename);

        //设置文件MIME类型
        response.setContentType(getServletContext().getMimeType(filename));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        //获取要下载的文件绝对路径，我的文件都放到WebRoot/download目录下
        ServletContext context=this.getServletContext();
        String fullFileName=context.getRealPath("/file/"+filename);

        //输入流为项目文件，输出流指向浏览器
        InputStream is=new FileInputStream(fullFileName);
        ServletOutputStream os =response.getOutputStream();

        /*
         * 设置缓冲区
         * is.read(b)当文件读完时返回-1
         */
        int len=-1;
        byte[] b=new byte[1024];
        while((len=is.read(b))!=-1){
            os.write(b,0,len);
        }
        //关闭流
        is.close();
        os.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--DownloadServlet->doPost()");
        this.doGet(req, resp);
    }

    // 什么也不做
    public void destroy() {
        System.out.println("DownloadServlet->destroy()");
        super.destroy();
    }
}
