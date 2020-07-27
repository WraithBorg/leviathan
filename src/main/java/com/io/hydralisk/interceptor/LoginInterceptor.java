package com.io.hydralisk.interceptor;

import com.alibaba.fastjson.JSON;
import com.io.hydralisk.annotate.WithoutLogin;
import com.io.hydralisk.constant.ErrCodeConst;
import com.io.hydralisk.constant.SessionConst;
import com.io.hydralisk.domain.UserInfo;
import com.io.hydralisk.mapper.UserInfoMapper;
import com.io.hydralisk.result.MsgResult;
import com.io.hydralisk.security.JwtUtil;
import com.io.hydralisk.service.usb.UserInfoService;
import com.io.hydralisk.util.CCommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private static final String LI_AUTH_CODE = "authcode";
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authCode = request.getParameter(LI_AUTH_CODE);
        if (CCommonUtils.isBlank(authCode)) {
            return true;
        }
        try {
            UserInfo userInfo = JwtUtil.parseUser(authCode);
            userInfoMapper.selectList(null);
            UserInfo userDB = userInfoService.getUserByTelephone(userInfo.getTelePhone());
            if (userDB == null) {
                logger.error("用户账户不存在" + ErrCodeConst.A0201);
                return false;
            }
            JwtUtil.isVerify(authCode, userDB);
            request.getSession().setAttribute(SessionConst.CURRENT_USER, userDB);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            WithoutLogin withoutLogin = handlerMethod.getMethodAnnotation(WithoutLogin.class);
            if (withoutLogin != null) {
                return true;
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            logger.error("登录信息失效，请重新登录！" + ErrCodeConst.A0210, e.getMessage());
            String toJSON = JSON.toJSONString(MsgResult.fail("登录信息失效，请重新登录！" + ErrCodeConst.A0210));
            PrintWriter out = response.getWriter();
            out.append(toJSON);
            return false;
        } catch (Exception e) {
            logger.error("登录信息异常！" + ErrCodeConst.A0210, e);
            return false;
        }


    }
}
