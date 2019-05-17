package com.team2.webservice.sprint1.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;
    private String content;
    private int cid;
    private int readCnt;

    @ManyToOne
    @JoinColumn(name = "sender")
    Member member;

}
