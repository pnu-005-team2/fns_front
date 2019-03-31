package com.team2.webservice.sprint1.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.*;

@Getter // lombook, getter/setter 자동으로 만들어줌
@Setter
@Entity // JPA에 의해 관리되는 엔티티임을 선언, 즉 테이블과 링크될 클래스임을 나타냄, 언더스코어 네이밍으로 매칭
@Table(name="testBoardVo") // 실제 맵핑될 테이블
public class testBoardVo {
    private int bno;
    private String subject;
    private String content;
    private String writer;
    private Date reg_date;



}
