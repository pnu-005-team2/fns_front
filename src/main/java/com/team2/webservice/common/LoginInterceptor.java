package com.team2.webservice.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;




@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN = "login";
    private static final Logger logger =LoggerFactory.getLogger(LoginInterceptor.class);
    //--------컨트롤러가 실행되기 이전 실행, 컨트롤러에 true면 접근, false면 접근하지 않음-------------
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute(LOGIN) != null){
            httpSession.removeAttribute(LOGIN); // 기존 로그인 정보를 삭제
        }
        return true;
    }

    //--------컨트롤러가 실행된 이후 실행, 컨트롤러에서 예외 발생시 실행되지 않음-------------
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws IOException {
        HttpSession httpSession = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object member = modelMap.get("user");

        if(member != null){
            logger.info("New Login Success");
            httpSession.setAttribute(LOGIN, member); // 세션에 로그인정보 저장
            logger.info("New Session : " + member.toString());
            response.sendRedirect("/timeline");
        }

    }



}
