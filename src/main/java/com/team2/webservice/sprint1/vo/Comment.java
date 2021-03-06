package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="comment")
public class Comment {
    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private int pid;
    private String content;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime created_date;

//    @ManyToOne
//    @JoinColumn(name ="pid")
//    private Board board;

    @ManyToOne
    @JoinColumn(name ="uid")
    private Member member;

}
