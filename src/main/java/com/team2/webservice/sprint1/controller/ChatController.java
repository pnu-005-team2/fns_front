package com.team2.webservice.sprint1.controller;


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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.team2.webservice.common.Constant.LOGIN;


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



    //-----------채팅방을 만듭니다.-----------
    @ResponseBody
    @RequestMapping(value = "create_room", method = RequestMethod.POST)
    public ChatRoom createRoom(String roomName,
                             @RequestParam("memberNames[]") String[] memberNames){
        logger.info("Create Room");
        ChatRoom chatRoom = chatService.createRoom(roomName, memberNames);
        return chatRoom;
    }

    //-----------채팅방 삭제합니다..-----------
    @MessageMapping(value = "exit_room/{roomId}")
    @SendTo("/topic/message/{roomId}")
    public Message exitRoom(@DestinationVariable String roomId,
                            String memberName){

        logger.info("Exit Room");
        logger.info("Room Id : "+roomId);
        logger.info("Name    : "+memberName);
        int intRoomId = Integer.parseInt(roomId);
        boolean isExit = chatService.exitRoom(intRoomId, memberName);

        if(isExit){
            logger.info("Exit Success");
            Message message = chatService.makeExitMessage(intRoomId, memberName);
            return message;
        }

        else logger.info("Exit Failed");
        return null;
    }
    //-----------채팅방 기록들을 모델에 담고 채팅방 화면을 리턴합니다.-----------
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String enterRoom(Model model, HttpServletRequest request,
                            @RequestParam("cid") int chatRoomId){
        logger.info("EnterRoom");
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute(LOGIN) == null){
            logger.info("로그인 계정이 존재하지 않습니다.");
            return "chat";
        }
        Member member = (Member)httpSession.getAttribute(LOGIN);
        ChatRoom chatRoom = chatService.loadRoomInfo(chatRoomId);
        List<ChatRoom> chatRoomlist = chatService.loadChatList(member);
        model.addAttribute("RoomList",chatRoomlist);
        model.addAttribute("chatRoom", chatRoom);
        return "chat";
    }

    //-----------채팅방리스트를 리턴합니다.-----------
    @ResponseBody // 객체를 json형식으로 변환하여 던져준다.
    @RequestMapping(value = "chat_list", method = RequestMethod.POST)
    public List<ChatRoom> loadList(Member member){
        logger.info("loadList");
        List<ChatRoom> chatRooms = chatService.loadChatList(member);
        return chatRooms;
    }

    //-----------채팅 메시지를 저장하고, 구독중인 클라이언트들에게 뿌려줍니다.-----------
    @ResponseBody
    @MessageMapping("chat/{roomId}")
    @SendTo("/topic/message/{roomId}")
    public Message chat(Message message, @RequestBody Map<String,String> data,
                        @DestinationVariable String roomId){
        logger.info("Chat in : " + roomId);
        String sender = data.get("userName");
        logger.info("UserName : " + sender);
        chatService.saveMessage(message,sender);
        return message;
    }

    @MessageMapping("info")
    @SendToUser("/queue/info")
    public String info(String message, SimpMessageHeaderAccessor messageHeaderAccessor){
        System.out.println("Info In");
        return message;
    }


}