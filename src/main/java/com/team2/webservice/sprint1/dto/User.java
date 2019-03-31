package com.team2.webservice.sprint1.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    private Long uid;
    private String id;
    private String password;
    private String gender;
}
