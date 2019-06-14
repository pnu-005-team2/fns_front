package com.team2.webservice.sprint1.dto;


import com.team2.webservice.sprint1.jpa.FriendsRepository;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class MyPageDTO {
    private int uid;
    private String name;
    private String gender;
    private String img;
    private String intro;
    private int follower_cnt;
    private int following_cnt;
    private int board_cnt;


    public MyPageDTO(Member member){
        this.uid = member.getUid();
        this.name = member.getName();
        this.gender= member.getGender();
        this.img = member.getImg();
        this.intro = member.getIntro();
    }
}
