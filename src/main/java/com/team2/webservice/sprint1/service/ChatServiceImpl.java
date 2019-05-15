package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatServiceImpl  implements ChatService{

    @Autowired
    MemberRepository memberRepository;


    @Override
    public List<Member> loadChatList(Member member) {

        //Todo findFriend 구현 필요
        List<Member> memberList = memberRepository.findAll();

        return memberList;
    }
}
