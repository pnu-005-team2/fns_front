package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Member;

public interface FriendsService {

    //친구추가 (memFrom이 memTo를 친구 추가함)
    public void addFriends(Member memFrom, Member memTo);
    //친구목록 보기
    public void showFriends(Member me);
    //친구 조회
    public Member findAFriend(Member me, String findName);
}