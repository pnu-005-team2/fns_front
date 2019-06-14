package com.team2.webservice.sprint1.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileDTO {

    private String email;
    private String name;
    private String intro;
    private MultipartFile img;

}
