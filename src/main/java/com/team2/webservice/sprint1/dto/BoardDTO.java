package com.team2.webservice.sprint1.dto;


import com.team2.webservice.sprint1.vo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDTO {

    public BoardDTO (Board board){
        this.pid = board.getPid();
        this.content = board.getContent();
        this.hashtag = board.getHashtag();
        this.img = board.getImg().toString();
        this.member = board.getMember();
        this.likeRecords = board.getLikeRecords();
//        this.comments = board.getComments();
//        this.productLinks = board.getProductLinks();
    }


    private Long pid;
    private String content;
    private String hashtag;
    private String img;
    private Member member;
    private List<LikeRecord> likeRecords;
//    private List<Comment> comments;
//    private List<ProductLink> productLinks;


}
