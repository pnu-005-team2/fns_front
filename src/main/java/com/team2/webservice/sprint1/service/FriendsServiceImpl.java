package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendsServiceImpl implements FriendsService{
    @Autowired
    MemberRepository memberRepository;


    @Override
    public void addFriends(Member memFrom, Member memTo) {

    }

    @Override
    public void showFriends(Member me) {

    }

    @Override
    public Member findAFriend(Member me, String findName) {
        return null;
    }
}
