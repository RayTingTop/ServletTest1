package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author RayTing
 * @date 2019-11-07 16:37
 * 文件上传处理
 */
public class UploadServlet extends HttpServlet {
    // 上传文件存储目录
    private static final String DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    // 执行必需的初始化
    public void init() throws ServletException {
        System.out.println("UploadServlet->doGet()");
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UploadServlet->doGet()");

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }


        DiskFileItemFactory factory = new DiskFileItemFactory();  // 配置上传参数
        factory.setSizeThreshold(MEMORY_THRESHOLD);// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // 设置临时存储目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);  // 设置最大文件上传值
        upload.setSizeMax(MAX_REQUEST_SIZE); // 设置最大请求值 (包含文件和表单数据)
        upload.setHeaderEncoding("UTF-8"); // 中文处理

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("/") + File.separator + DIRECTORY;


        File uploadDir = new File(uploadPath);  // 如果目录不存在则创建
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName(); //文件名
                        String filePath = uploadPath + File.separator + fileName; //路径
                        File storeFile = new File(filePath); //创建文件
                        // 保存文件到硬盘
                        item.write(storeFile); //写出文件
                        System.out.println("文件上传成功："+filePath);
                        request.setAttribute("message", "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "错误信息: " + ex.getMessage());
        }
        // 跳转到 index.jsp
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--UploadServlet->doPost()");
        this.doGet(req, resp);
    }

    // 什么也不做
    public void destroy() {
        System.out.println("UploadServlet->destroy()");
        super.destroy();
    }

}
