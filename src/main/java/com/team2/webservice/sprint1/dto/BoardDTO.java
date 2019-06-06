package com.team2.webservice.sprint1.dto;


import com.team2.webservice.sprint1.jpa.CommentRepository;
import com.team2.webservice.sprint1.vo.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
public class BoardDTO {

    public BoardDTO (Board board, Pageable comment_pageable){
        this.pid = board.getPid();
        this.content = board.getContent();
        this.hashtag = board.getHashtag();
        this.member = board.getMember();
        //this.likeRecords = board.getLikeRecords();
    }


    private int pid;
    private String content;
    private String hashtag;
    private Member member;
    //private List<LikeRecord> likeRecords;
    private List<Comment> comments;
//    private String img;
//    private List<ProductLink> productLinks;


}
