package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Board implements Serializable, Comparable<Board> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private int pid;
    private String content;
    private Blob img;
    private String hashtag;

    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "email", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<LikeRecord> likeRecords;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pid")
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<ProductLink> productLinks;

    @Override
    public int compareTo(Board o) {
        if(this.pid > o.pid)
            return -1;
        else
            return 1;
    }
}
