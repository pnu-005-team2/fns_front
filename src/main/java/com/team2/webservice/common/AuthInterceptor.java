package com.team2.webservice.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN = "login";
    private static final Logger logger =LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if ( httpSession.getAttribute(LOGIN) == null ){
            // 로그인이 안되어 있는 상태임으r로 로그인 폼으로 다시 돌려보냄(redirect)
            logger.info("로그인이 필요한 페이지입니다.");
            response.sendRedirect("/login");
            return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
        } else{
            logger.info("세션 연장.");
            httpSession.setMaxInactiveInterval(30*60); // 세션시간 만료시간 설정
            return true;
        }// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
        // 따라서 true로하면 컨트롤러 uri로 가게 됨.
    }


}
