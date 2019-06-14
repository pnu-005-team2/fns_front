package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.ChatRoom;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Message;

import java.util.List;

public interface ChatService {

    List<ChatRoom> loadChatList(Member uid);
    ChatRoom loadRoomInfo(int chatRoomId);
    ChatRoom createRoom(String roomName, String[] memberNames);
    boolean exitRoom(int cid, String memberName);
    void saveMessage(Message message, String sender);
}
