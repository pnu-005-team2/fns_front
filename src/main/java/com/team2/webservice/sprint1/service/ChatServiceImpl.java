package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.ChatMemberRepository;
import com.team2.webservice.sprint1.jpa.ChatRoomRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.ChatMember;
import com.team2.webservice.sprint1.vo.ChatRoom;
import com.team2.webservice.sprint1.vo.Member;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl  implements ChatService{

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatMemberRepository chatMemberRepo;

    @Override
    public List<ChatRoom> loadChatList(Member member) {

        System.out.println("LoadChatList");
        System.out.println(member.getEmail());
        Optional<Member> memberO= memberRepository.findByEmail(member.getEmail());

        if(!memberO.isPresent()){
            System.out.println("로그인 계정이 존재하지 않습니다");
            return null;
        }

        Optional<List<ChatMember>> chatMembers = chatMemberRepo.findByMember(memberO.get());

        if(!chatMembers.isPresent()){
            System.out.println("채팅목록이 존재하지 않습니다");
            return null;
        }
        //Todo findFriend 구현 필요
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        return chatRooms;
    }
}
