package com.team2.webservice.sprint1.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private Long pid;
    private String content;
    private String writer;
    private String imgUrl;
    private String likeIds;
    private String hashtags;

}
