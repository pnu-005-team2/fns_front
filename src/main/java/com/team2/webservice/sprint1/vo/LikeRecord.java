package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter // lombook, getter/setter 자동으로 만들어줌
@Setter
@ToString
@Entity // JPA에 의해 관리되는 엔티티임을 선언, 즉 테이블과 링크될 클래스임을 나타냄, 언더스코어 네이밍으로 매칭
@Table(name="likerecord") // 실제 맵핑될 테이블
public class LikeRecord {
    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄, 가본키 생성을 DB가 수행하도록 선언
    private Long lid;
    // DTO 클래스의 필드는 각각 테이블의 Column의 해당한다.
    private Boolean like_boolean;

    // uid를 외부키로 갖는다, referencedColumnName 속성을 넣어주면 상대 엔티티에서 참조할 컬럼을 지정 할 수 있다
    // 참조엔티티의 컬럼과 외부키 이름이 같을 경우 생략 가능하다.
    @ManyToOne
    @JoinColumn(name = "uid")
    Member member; // member에서 mappedBy의 선언된 스트링과 같아야한다.

    @ManyToOne
    @JoinColumn(name = "pid")
    Board board;


}
