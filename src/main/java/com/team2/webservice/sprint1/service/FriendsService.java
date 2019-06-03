package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;

import java.util.List;

public interface FriendsService {

    //친구추가 (memFrom이 memTo를 친구 추가함)
    public Member addFriends(int uid1, int uid2);
    public void deleteFriend(int uid1, int uid2);
    //친구목록 보기
    public List<Member> showFriends(Member me);
    public List<Member> showFriended(Member me);
    //친구 게시물 수집
    public List<Board> showFBoard(Member me);
    //친구 조회
    public Member findAFriend(Member me, String findName);
    //친구 추천
    public List<Member> recommendFriends(Member me);
}