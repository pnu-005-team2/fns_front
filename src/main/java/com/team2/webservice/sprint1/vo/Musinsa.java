package com.team2.webservice.sprint1.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Musinsa")
public class Musinsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String img_url;
    private String link;
    private String description;
    @Column(name = "parsing_date")
    private LocalDate parsingDate;

}
