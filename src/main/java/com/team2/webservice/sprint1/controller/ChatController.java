package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.jpa.ChatRoomRepository;
import com.team2.webservice.sprint1.service.ChatServiceImpl;
import com.team2.webservice.sprint1.vo.ChatRoom;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class ChatController {

    @Autowired
    ChatServiceImpl chatService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String enterRoom(Model model, @RequestParam("cid") int chatRoomId){
        System.out.println("EnterRoom");
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(chatRoomId);
        model.addAttribute("chatRoom", chatRoom.get());
        return "chatTest";
    }

    @RequestMapping(value = "chat_list", method = RequestMethod.POST)
    @ResponseBody
    public List<ChatRoom> loadList(Model model, Member member){
        List<ChatRoom> chatRooms = chatService.loadChatList(member);
        System.out.println("Complete!");
        return chatRooms; //Todo json형식으로 변환 필요
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
