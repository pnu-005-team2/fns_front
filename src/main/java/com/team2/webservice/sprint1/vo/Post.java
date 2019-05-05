package com.team2.webservice.sprint1.vo;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private Long pid;
    private String content;
    private Blob img;
    private String hashtag;

    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "name", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<LikeRecord> likeRecords;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<ProductLink> productLinks;

}
