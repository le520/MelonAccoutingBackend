package com.nwpu.melonbookkeeping.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author noorall
 * @date 2021/1/84:14 下午
 * @Description: admin自定义拦截规则
 */
@Component
public class AdminHandlerInterceptor implements HandlerInterceptor {
    /**
     * 后台拦截器
     * @param request request
     * @param response response
     * @param handler handler
     * @return 配置结果
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
