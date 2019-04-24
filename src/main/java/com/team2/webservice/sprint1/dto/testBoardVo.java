/*package com.team2.webservice.sprint1.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import javax.persistence.*;

@Getter // lombook, getter/setter 자동으로 만들어줌
@Setter
@Entity // JPA에 의해 관리되는 엔티티임을 선언, 즉 테이블과 링크될 클래스임을 나타냄, 언더스코어 네이밍으로 매칭
@Table(name="post") // 실제 맵핑될 테이블
public class testBoardVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private int pid;
    private String content;
    private String writer;
    private String img;
    private String hashtag;

}
<<<<<<< HEAD
*/
=======

>>>>>>> 84b175acf1703fe6845c3c585be3745ca443dde8
