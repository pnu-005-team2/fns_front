package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.service.MemberServiceImpl;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

// 모델은 수명이 딱 그 페이지만임!

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(Model model)
    {
        System.out.println("login");
        model.addAttribute("isOk", "empty");
        return "login";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String login(LoginDTO loginDTO, HttpServletRequest request, HttpSession session, Model model)
    {
        String return_page = "login";
        //Todo 비밀번호 암호화 필요
        Optional<Member> member = memberService.login(loginDTO);
        if(member.isPresent()){ // 계정존재 확인
                model.addAttribute("isOk", "true");
                model.addAttribute("user", member.get());
                System.out.println("로그인 성공");
        } else {
            System.out.println("일치하는 계정이 존재하지 않습니다.");
            model.addAttribute("isOk", "false");
        }
        System.out.println(return_page);
        return return_page;
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("isOk", "empty");
        System.out.println("로그아웃 완료");
        return "login";
    }
}
