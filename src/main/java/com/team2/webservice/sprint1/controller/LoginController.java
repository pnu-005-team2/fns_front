package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.service.MemberServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

// 모델은 수명이 딱 그 페이지만임!

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String view(Model model)
    {

        logger.info("login");
        model.addAttribute("isOk", "empty");
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginDTO loginDTO, HttpServletRequest request, HttpSession session, Model model)
    {
        String return_page = "login";
        //TODO 비밀번호 암호화 필요
        Optional<Member> member = memberService.login(loginDTO);
        if(member.isPresent()){ // 계정존재 확인
                model.addAttribute("isOk", "true");
                model.addAttribute("user", member.get());
                logger.info("로그인 성공");
        } else {
            logger.info("일치하는 계정이 존재하지 않습니다.");
            model.addAttribute("isOk", "false");
        }
        logger.info(return_page);
        return return_page;
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("isOk", "empty");
        logger.info("로그아웃 완료");
        return "login";
    }
}
