package com.team2.webservice.sprint1.controller;


import com.team2.webservice.common.LoginInterceptor;
import com.team2.webservice.sprint1.jpa.ChatRoomRepository;
import com.team2.webservice.sprint1.service.ChatServiceImpl;
import com.team2.webservice.sprint1.vo.ChatRoom;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/*Todo
    1. 채팅방 만들기 및 나가기 구현필요
    2. 메시지 보낸 시간 구현
    3. 읽은 사람 수 구현
 */
@Controller
public class ChatController {

    private static final Logger logger =LoggerFactory.getLogger(ChatController.class);

    @Autowired
    ChatServiceImpl chatService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    //-----------채팅방 기록들을 모델에 담고 채팅방 화면을 리턴합니다.-----------
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String enterRoom(Model model, @RequestParam("cid") int chatRoomId){
        logger.info("EnterRoom");
        ChatRoom chatRoom = chatService.loadRoomInfo(chatRoomId);
        model.addAttribute("chatRoom", chatRoom);
        return "chatTest";
    }

    //-----------채팅방리스트를 리턴합니다.-----------
    @RequestMapping(value = "chat_list", method = RequestMethod.POST)
    @ResponseBody // 객체를 json형식으로 변환하여 던져준다.
    public List<ChatRoom> loadList(Member member){
        logger.info("loadList");
        List<ChatRoom> chatRooms = chatService.loadChatList(member);
        return chatRooms;
    }

    @MessageMapping("info")
    @SendToUser("/queue/info")
    public String info(String message, SimpMessageHeaderAccessor messageHeaderAccessor){
        System.out.println("Info In");
        return message;
    }


    //-----------채팅 메시지를 저장하고, 구독중인 클라이언트들에게 뿌려줍니다.-----------
    @MessageMapping("chat/{roomId}")
    @SendTo("/topic/message/{roomId}")
    @ResponseBody
    public Message chat(Message message, @RequestBody Map<String,String> data,
//                        String sender,// data[sender]만 가져올 수 없나?
                        @DestinationVariable String roomId){
        logger.info("Chat in : " + roomId);
        String sender = data.get("sender");
        chatService.SaveMessage(message,Integer.parseInt(sender));
        return message;
    }


}