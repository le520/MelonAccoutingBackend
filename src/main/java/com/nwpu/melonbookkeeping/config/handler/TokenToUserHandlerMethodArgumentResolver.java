package com.nwpu.melonbookkeeping.config.handler;

import com.nwpu.melonbookkeeping.config.annotation.TokenToUser;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenToUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    UserService userService;

    public TokenToUserHandlerMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = null;
        if (methodParameter.getParameterAnnotation(TokenToUser.class) != null) {
            String accessToken = nativeWebRequest.getHeader("Authorization");
            int userId = TokenProvider.getUserId(accessToken);
            if (userId != -1) {
                user = userService.getUserById(TokenProvider.getUserId(accessToken));
            }
        }
        return user;
    }
}
