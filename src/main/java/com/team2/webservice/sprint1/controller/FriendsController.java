package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class FriendsController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FriendsServiceImpl friendsService;


    @ResponseBody
    @RequestMapping(value = "/friend/add" , method = RequestMethod.POST)
    public boolean friendAdd(int uid1, int uid2, Model model, HttpServletRequest request){
        //1: 추가행위를 하는사람(following 에 추가) 2:추가행위를 당하는사람(follower에 추가)
        System.out.println("FriendAdd");
        boolean canadd = friendsService.addFriends(uid1, uid2);
        return canadd;

    }

    @ResponseBody
    @RequestMapping(value = "/fried/delete" , method = RequestMethod.POST)
    public int friendDelete(int uid1, int uid2, Model model, HttpServletRequest request){
        //1: 삭제행위를 하는사람, 2: 삭제 당하는사람
        friendsService.deleteFriend(uid1, uid2);
        friendsService.setFriendListToModel(model, request);
        return uid2;
    }

    @RequestMapping(value = "/load/friend", method = RequestMethod.POST)
    @ResponseBody
    public List<Member> loadFriends(int uid, String keyword){
        //Todo keyword에 따라 일치하는 친구들 뿌려주기
        Optional<Member> me = memberRepository.findById(uid);
        Optional<List<Member>> test = memberRepository.findByUidAndNameLike(uid, keyword);
        for (int i = 0; i < test.get().size(); i++) {
            System.out.println("Test");
            System.out.println(test.get().get(i).getName());
        }

        if(!me.isPresent()){
            System.out.println("올바르지 않은 계정입니다.");
            return null;
        }
        return friendsService.showFollowing(me.get());

    }
}

