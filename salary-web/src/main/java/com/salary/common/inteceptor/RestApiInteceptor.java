package com.salary.common.inteceptor;

import com.salary.common.contants.ExceptionEnum;
import com.salary.common.contants.JwtConstants;
import com.salary.common.core.R;
import com.salary.common.exception.BizException;
import com.salary.common.util.JwtTokenUtil;
import com.salary.common.util.ToolUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * api接口拦截器
 *
 * @author sun
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        if (request.getServletPath().equals(JwtConstants.AUTH_PATH)) {
            return true;
        }
        final String requestHeader = request.getHeader(JwtConstants.AUTH_HEADER);
        //验证token是否过期,包含了验证jwt是否正确
        try {
            boolean flag = JwtTokenUtil.isTokenExpired(requestHeader);
            if (flag) {
                ToolUtil.renderJson(response, new BizException(ExceptionEnum.TOKEN_EXPIRED.getCode(), ExceptionEnum.TOKEN_EXPIRED.getMessage()));
                return false;
            }
        } catch (JwtException e) {
            //有异常就是token解析失败
            ToolUtil.renderJson(response, R.error("token校验失败"));
            return false;
        }
        return true;
    }
}
