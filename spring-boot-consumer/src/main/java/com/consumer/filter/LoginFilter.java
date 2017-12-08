package com.consumer.filter;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 *
 * 一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。
 * 也可以通过实现 ServletContextInitializer 接口直接注册。
 *
 * 二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过
 * @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
 *
 * </p>
 * @author wangliang
 * @since 2017/10/12
 */
//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    private FilterConfig config;

    //sso单点登录服务器地址
    @Value("${ssoServerUrl}")
    private String ssoServerUrl;

    //sso单点登录服务器登录地址
    @Value("${ssoServerLoginUrl}")
    private String ssoServerLoginUrl;

    //sso单点登录服务器校验token接口地址
    @Value("${ssoServerVerifyTokenUrl}")
    private String ssoServerVerifyTokenUrl;

    //客户端地址（后台管理系统地址）
    @Value("${clientUrl}")
    private String clientUrl;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Override
    public void destroy() {
    }

    /**
     * 验证token信息
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        // 已经登录，放行
        if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
            chain.doFilter(req, res);
            return;
        }

        // 从认证中心回跳的带有token的请求，有效则放行
        String token = request.getParameter(AuthConst.TOKEN);
        if (token != null) {
            boolean verifyResult = this.verifyToken(token);
            if (!verifyResult) {
                response.sendRedirect(ssoServerLoginUrl);
                return;
            }
            chain.doFilter(request, response);
            //将当前局部会话标记为“已登录”
            session.setAttribute(AuthConst.IS_LOGIN, true);
//            session.setAttribute(AuthConst.TOKEN, token);
            // 存储，用于注销
            IMap map =hazelcastInstance.getMap("token");
            map.put(token, session);
            chain.doFilter(req, res);
            return;
        }

        // 重定向至sso认证中心，并将自己的地址作为参数
        String uri = ssoServerUrl + "?goto=" + clientUrl;
        response.sendRedirect(uri);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    /**
     * 验证token是否有效。如果有效服务器端将返回用户身份
     */
    public boolean verifyToken(String token) {
        boolean verifyResult = false;
        return verifyResult;
    }
}
