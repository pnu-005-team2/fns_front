package com.team2.webservice.sprint1.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private Long pid;
    private String writer;
    private String content;
    private String Date;
}
