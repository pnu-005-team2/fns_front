package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.service.MemberServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class JoinController {

    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(){
        return "join";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(Member member, HttpServletRequest request)
    {
        System.out.println(member);
        System.out.println("Post");
        memberService.register(member);
        return "redirect:/login";
    }
}
