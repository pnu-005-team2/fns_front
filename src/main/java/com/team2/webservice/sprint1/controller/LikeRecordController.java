package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.LikeRecord;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LikeRecordController {
    @Autowired
    private LikeRecordRepository likeRecordRepository;

    @RequestMapping("/test")
    public String view(Model model)
    {
        System.out.println("In LikeRecord");
        List<LikeRecord> likeRecordList = likeRecordRepository.findAll(); // select * from LikeRecord
        model.addAttribute("LikeList", likeRecordList);
        System.out.println(likeRecordList.get(0).getLid());
        return "LikeTest";
    }

    //#.DB에서 게시물 받아옴
    //#.각 게시물의 이름(id), 게시물 내용(글, 이미지), 좋아요 갯수
    //#.뿌려주기 : 접속하는 순간, 지속적으로 데이터를 받으며, 게시를 하는 사람과 frined라면 보이도록
    //#.friend가 아니라면 안뿌려주기
    //#.
}
