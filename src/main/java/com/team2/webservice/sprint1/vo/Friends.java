package com.team2.webservice.sprint1.vo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.*;

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


}

