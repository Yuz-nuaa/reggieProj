package com.zyfproject.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.zyfproject.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 登陆拦截过滤器
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 本地请求的uri
        String requestURI = request.getRequestURI();

        log.info("Filter requestURI:{}", requestURI);
        // 不需要进行过滤处理
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
        };

        // 路径匹配器进行比较
        boolean check = check(urls, requestURI);

        if(check){
            log.info("Filter PASS:{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 判断登陆状态
        if(request.getSession().getAttribute("employee") != null){
            log.info("User has login:{}", requestURI);
            long id = Thread.currentThread().getId();
            log.info("Thread id is: {}", id);
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录返回未登录结果
        log.info("User has not login:{}", requestURI);
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


        return;
    }

    /**
     * 路径匹配检查
     *
     * @param urls
     * @param requestUrl
     * @return
     */
    public boolean check(String[] urls, String requestUrl){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestUrl);
            if(match){
                return true;
            }
        }
        return false;
    }
}
