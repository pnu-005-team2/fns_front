package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Friends;
import com.team2.webservice.sprint1.vo.Member;

import java.util.List;

public interface FriendsService {

    //친구추가 (memFrom이 memTo를 친구 추가함)
    public Friends addFriends(int uid1, int uid2);
    public void deleteFriend(int uid1, int uid2);
    //친구목록 보기
    public List<Member> showFollowing(int me);
    public List<Member> showFollower(int me);
    //친구 게시물 수집
    public List<Board> showFBoard(Member me);
    //친구 조회
    public Member findAFriend(Member me, String findName);
    //친구 추천
    public List<Member> recommendFriends(Member me);
}