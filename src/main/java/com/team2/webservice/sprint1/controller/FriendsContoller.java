package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.FriendsService;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/timeline2")
public class FriendsContoller {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FriendsServiceImpl friendsService;

    @RequestMapping(value = "/timeline" , method = RequestMethod.GET)
    public String showList(Model model, Member member){

        //show following
        List<Member> Friends = friendsService.showFriends(member);

        model.addAttribute("friendsRecordList", Friends);
        model.addAttribute("friendsRecordList_Byte", Friends);

        //show follower
        List<Member> Friended = friendsService.showFriended(member);

        model.addAttribute("friendedRecordList", Friended);
        model.addAttribute("friendedRecordList_Byte", Friended);

        return "timelineVer2";
    }

    public void add(Member member1, Member member2){
        //1: 추가행위를 하는사람(following 에 추가) 2:추가행위를 당하는사람(follower에 추가)
        friendsService.addFriends(member1, member2);
    }
}
