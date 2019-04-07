package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LikeRecordController {
    @Autowired
    private LikeRecordRepository likeRecordRepository;
    @Autowired
    private MemberRepository userRepository;

    @RequestMapping("/test")
    public String view(Model model)
    {
        System.out.println("In LikeRecord");
        List<LikeRecord> likeRecordList = likeRecordRepository.findAll(); // select * from LikeRecord
        List<Member> userRecordList = userRepository.findAll(); // select * from LikeRecord
        model.addAttribute("LikeList", likeRecordList);
        System.out.println(likeRecordList.get(0).getLid());
        System.out.println(userRecordList.get(0).getGender());

        return "LikeTest";
    }

    @ResponseBody
    @PostMapping
    @RequestMapping("/like_btn")
    public String LikeBtn_Event_Ctr(HttpServletRequest httpServletRequest){


        String likeBtn_pid= httpServletRequest.getParameter("pid");
        String likeBtn_uid =httpServletRequest.getParameter("uid");

        String likeBtn_like_boolean = httpServletRequest.getParameter("like_boolean");



        LikeRecord likeRecord = new LikeRecord();
        likeRecord.setPid(Long.parseLong(likeBtn_pid));
        likeRecord.setUid(Long.parseLong(likeBtn_uid));
        likeRecord.setLike_boolean(Boolean.parseBoolean(likeBtn_like_boolean));

        likeRecordRepository.save(likeRecord);


        return "Timeline";
    }




    //#.DB에서 게시물 받아옴
    //#.각 게시물의 이름(id), 게시물 내용(글, 이미지), 좋아요 갯수
    //#.뿌려주기 : 접속하는 순간, 지속적으로 데이터를 받으며, 게시를 하는 사람과 frined라면 보이도록
    //#.friend가 아니라면 안뿌려주기
    //#.
}
