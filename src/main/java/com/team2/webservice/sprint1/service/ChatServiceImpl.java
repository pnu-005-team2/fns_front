package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.ChatMemberRepository;
import com.team2.webservice.sprint1.jpa.ChatRoomRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.jpa.MessageRepository;
import com.team2.webservice.sprint1.vo.ChatMember;
import com.team2.webservice.sprint1.vo.ChatRoom;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl  implements ChatService{
    private static final Logger logger =LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatMemberRepository chatMemberRepo;

    @Autowired
    MessageRepository messageRepo;

    //-----------------채팅방 목록을 불러옵니다.-------------------
    @Override
    public List<ChatRoom> loadChatList(Member member) {

        logger.info("LoadChatList");
        Optional<Member> memberO= memberRepository.findByEmail(member.getEmail());

        if(!memberO.isPresent()){
            logger.info("로그인 계정이 존재하지 않습니다");
            return null;
        }

        Optional<List<ChatMember>> chatMembers = chatMemberRepo.findByMember(memberO.get());

        if(!chatMembers.isPresent()){
            logger.info("채팅목록이 존재하지 않습니다");
            return null;
        }

        List<ChatRoom> chatRooms = new ArrayList<>();

        //채팅방목록 불러오기
        for(ChatMember chatMember: chatMembers.get()){
            Optional<ChatRoom> chatRoom = chatRoomRepository.findById(chatMember.getCid());
            if(!chatRoom.isPresent()){
                logger.info("채팅방목록에 존재하지 않는 방입니다");
                return null;
            }
            chatRooms.add(chatRoom.get());
        }

        return chatRooms;
    }


    //-----------------채팅방 기록을 불러옵니다.-------------------
    @Override
    public ChatRoom loadRoomInfo(int chatRoomId){
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(chatRoomId);
        if(chatRoom.isPresent()){
            return chatRoom.get();
        } else{
            logger.error("방정보가 존재하지 않습니다.");
            return null;
        }
    }

    //-----------------메시지를 저장합니다.-------------------
    @Override
    public void SaveMessage(Message message, int sender){
        message.setMember(this.findSender(sender)); // 조인객체는 직접 찾아줘야하나?
        messageRepo.save(message);
        logger.info("메시지 저장완료");
    }

    //-----------------발신자를 찾습니다.-------------------
    public Member findSender(int uid){
        Optional<Member> member = memberRepository.findById(uid);
        if(member.isPresent()) return member.get();
        else{
            logger.error("올바른 계정이 아닙니다.");
            return null;
        }
    }

}
