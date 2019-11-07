package filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author RayTing
 * @date 2019-11-07 11:20
 */
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site = filterConfig.getInitParameter("Site");
        // 输出初始化参数
        System.out.println("过滤器名称: " + site);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name = new String (servletRequest.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        // 过滤器核心代码逻辑
        System.out.println("MyFilter-doFilter:"+name);

        if(!name.equals("")){
            // 把请求传回过滤链
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //设置返回内容类型
            servletResponse.setContentType("text/html;charset=UTF-8");

            //在页面输出响应信息
            PrintWriter out = servletResponse.getWriter();
            out.print("<b>name不能为空，请求被拦截，不能访问web资源</b>");
            System.out.println("name不能为空，请求被拦截，不能访问web资源");
        }

    }

    @Override
    public void destroy() {

    }
}
