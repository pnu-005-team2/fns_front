package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeline")
public class FriendsContoller {
    @Autowired
    MemberRepository memberRepository;





}
