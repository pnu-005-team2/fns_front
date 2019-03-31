package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.LikeRecord;
import com.team2.webservice.sprint1.dto.User;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LikeRecordController {
    @Autowired
    private LikeRecordRepository likeRecordRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public String view(Model model)
    {
        System.out.println("In LikeRecord");
        List<LikeRecord> likeRecordList = likeRecordRepository.findAll(); // select * from LikeRecord
        List<User> userRecordList = userRepository.findAll(); // select * from LikeRecord
        model.addAttribute("LikeList", likeRecordList);
        System.out.println(likeRecordList.get(0).getLid());
        System.out.println(userRecordList.get(0).getGender());

        return "LikeTest";
    }
}
