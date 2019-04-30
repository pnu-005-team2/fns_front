package com.team2.webservice.sprint1.vo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="productlink")
public class ProductLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private Long plid;
    private Long pid;
    private int position_x;
    private int position_y;
    private String linkText;

//    @ManyToOne
//    @JoinColumn(name ="pid")
//    Post post;
}
