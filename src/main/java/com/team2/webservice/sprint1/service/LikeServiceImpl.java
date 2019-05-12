package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LikeRecordRepository likeRecordRepository;

    @Override
    public void registerLike(LikeRecord likeRecord, String name, Long pid){
        System.out.println("------Register Like--------");
        this.setMember(likeRecord, name);
        this.setPost(likeRecord, pid);
        System.out.println(likeRecord.getLid());
        System.out.println(likeRecord.getMember());

        likeRecordRepository.save(likeRecord);
        System.out.println("------Completed Save------");
    }

    @Override
    public Optional<Member> setMember(LikeRecord likeRecord, String name){
        Optional<Member> member = memberRepository.findByName(name);
        if(member.isPresent())
            likeRecord.setMember(member.get());
        else
            System.out.println("해당 계정이 존재하지 않습니다.");
        return member;
    }

    @Override
    public Optional<Board> setPost(LikeRecord likeRecord, Long pid) {
        Optional<Board> post = boardRepository.findById(pid);
        if(post.isPresent())
            likeRecord.setBoard(post.get());
        else
            System.out.println("해당 게시물이 존재하지 않습니다.");
        return post;
    }

}
