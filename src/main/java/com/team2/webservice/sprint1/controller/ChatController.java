package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.ChatServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.team2.webservice.common.Constant.SESSION;

@Controller
public class ChatController {

    @Autowired
    ChatServiceImpl chatService;

    @Autowired
    private MemberRepository memberRepository;
    List<Member> memberList;


    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String view(ModelMap modelMap){

        memberList=memberRepository.findAll();
        modelMap.addAttribute("memberList", memberList);
        // todo 친구 목록으로 변경 (@hwp)
        return "chat";

        //return "chat";
    }

    @RequestMapping(value = "chat", method = RequestMethod.POST)
    public List<Member> loadList(Member member){
        return chatService.loadChatList(member);
    }

    @MessageMapping("info")
    @SendToUser("/queue/info")
    public String info(String message, SimpMessageHeaderAccessor messageHeaderAccessor){
        System.out.println("Info In");
        return message;
    }

    @MessageMapping("chat")
    @SendTo("/topic/message")
    public String chat(String message, SimpMessageHeaderAccessor messageHeaderAccessor){
        System.out.println("Chat In");
        return message;
    }


}