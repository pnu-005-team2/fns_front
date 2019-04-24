package com.team2.webservice.sprint1.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // JPA에 의해 관리되는 엔티티임을 선언, 즉 테이블과 링크될 클래스임을 나타냄, 언더스코어 네이밍으로 매칭
@Table(name="comment")
public class Comment {
    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private Long pid;
    private String date;
    private String writer;
    private String content;
}
