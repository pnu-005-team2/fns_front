package com.team2.webservice.sprint1.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    private Long pid;
    private String content;
    private String writer;
    private String img;
    private String hashtags;
}
