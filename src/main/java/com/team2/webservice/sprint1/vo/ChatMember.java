package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "chat_member")
public class ChatMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmid;
    private String role;

    @OneToOne
    @JoinColumn(name = "mid")
    private Member member;

}
