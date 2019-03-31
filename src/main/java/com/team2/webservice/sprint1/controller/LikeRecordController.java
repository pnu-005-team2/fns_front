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
}
