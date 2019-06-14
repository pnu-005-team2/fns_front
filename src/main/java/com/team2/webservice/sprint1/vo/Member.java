package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="member")
public class Member implements Serializable {
    // Serializable을 구현받지 않으면, cnnot be cast to java.io.Serializable] with root cause 에러가 출력
    // 왜 그런지는 공부를 해봐야함, 추측으로는 외부키가 String이기때문에, 직렬화가 필요한게 아닌가싶음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String email;
    private String password;
    private String gender;
    private String name;
    private String intro;
    private String img;
    private String friends; //my following
    private String friended; //follower

    // post에 관계과 종속되어 있음을 의미, board.java에서 member를 가르키는 변수명이랑 같아야한다.
    // OneToMany는 기본적으로 패치전략이 LAZY, 우선적으로 Proxy가 채워지고 필요할 때 가져온다.
    // cascade = CascadeType.REMOVE는 회원정보 삭제시, 게시물도 같이 삭제함시킨다.
    // 외래키는 post에 있다.
//    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
//    private List<Board> boards;

//    @OneToMany(mappedBy = "member")
//    private List<LikeRecord> likeRecords;

}
