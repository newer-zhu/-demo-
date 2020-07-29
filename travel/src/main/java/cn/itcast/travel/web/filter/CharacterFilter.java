package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //父接口强转为子接口
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取请求方法
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("get"))
            request.setCharacterEncoding("utf-8");
        //处理响应编码
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
