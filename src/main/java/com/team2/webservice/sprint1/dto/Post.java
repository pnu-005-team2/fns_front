package com.team2.webservice.sprint1.dto;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private Long pid;
    private String content;
    private String writer;

    private Blob img;
    private String hashtag;

}
