package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.Member;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Controller
@RequestMapping("/join")
public class JoinController {
    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(value = "")
    public String create(Member member){
//        memberRepository.save(member);
        return "join";
    }
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request,Model model)
    {
        Member member = new Member();
        member.setEmail(request.getParameter("userID"));
        member.setPassword(request.getParameter("userPassword"));
        member.setGender(request.getParameter("userGender"));
        memberRepository.save(member);

        System.out.println("Register Complete");

        return "login";
    }
}
