package com.team2.webservice.sprint1.dto;


import com.team2.webservice.sprint1.vo.Member;
import lombok.Data;

@Data
public class MyPageDTO {
    private int uid;
    private String name;
    private String gender;
    private String img;
    private String intro;
    private int follower_cnt;
    private int following_cnt;


    public MyPageDTO(Member member){
        this.uid = member.getUid();
        this.name = member.getName();
        this.gender= member.getGender();
        this.img = member.getImg();
        this.intro = member.getIntro();
        follower_cnt = member.getFriended().split(",").length;
        following_cnt = member.getFriends().split(",").length;
    }
}
