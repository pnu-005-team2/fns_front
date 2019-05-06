package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.vo.Member;

import java.util.Optional;

public interface LikeService {

    //종아요 등록
    void registerLike(LikeRecord likeRecord, String name, Long pid) throws Exception;

    Optional<Member> setMember(LikeRecord likeRecord, String name);
    Optional<Board> setPost(LikeRecord likeRecord, Long pid);

}
