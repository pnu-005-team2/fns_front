package com.team2.webservice.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    private Long pid;
    private String content;
    private String writer;
    private String img;
    private String hashtag;

}
