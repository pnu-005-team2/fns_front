package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "chat_member")
public class ChatMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmid;
    private int cid;
    private String role;
    private int uid;
//    @OneToOne
//    @JoinColumn(name = "uid")
//    private Member member;

}
