package com.team2.webservice.sprint1.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Table(name="friends")
@Entity
public class Friends{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private int myuid;
    private int youruid;
    private String yourname;

    //Todo 이게 맞는데....
//    @ManyToOne
//    @JoinColumn(name = "myuid")
//    private Member following;
//
//    @ManyToOne
//    @JoinColumn(name = "youruid")
//    private Member follower;


}

