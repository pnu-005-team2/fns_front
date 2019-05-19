//package com.team2.webservice.sprint1.controller;
//
//
//import com.team2.webservice.sprint1.jpa.MemberRepository;
//import com.team2.webservice.sprint1.service.FriendsService;
//import com.team2.webservice.sprint1.service.FriendsServiceImpl;
//import com.team2.webservice.sprint1.vo.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@RequestMapping("/timeline")
//public class FriendsContoller {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    FriendsServiceImpl friendsService;
//
//    @RequestMapping(value = "" , method = RequestMethod.GET)
//    public String showList(Model model, HttpServletRequest request){
//
//        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("login"));
//        //show following
//
//        Member me = (Member)session.getAttribute("login");
//        List<Member> Friends = friendsService.showFriends(me);
//
//        model.addAttribute("friendsRecordList", Friends);
//        model.addAttribute("friendsRecordList_Byte", Friends);
//
//        //show follower
//        List<Member> Friended = friendsService.showFriended(me);
//
//        model.addAttribute("friendedRecordList", Friended);
//        model.addAttribute("friendedRecordList_Byte", Friended);
//
//        System.out.println(Friends.size());
//        for(int i = 0 ; i < Friends.size() ; i++){
//            System.out.println(Friends.get(i).getName());
//        }
//        for(int i = 0; i< Friended.size() ; i++){
//            System.out.println(Friended.get(i).getName());
//        }
//
//        return "timeLineVer2";
//    }
//
////    @RequestMapping(method = RequestMethod.GET)
//    public void add(Member member1, Member member2){
//        //1: 추가행위를 하는사람(following 에 추가) 2:추가행위를 당하는사람(follower에 추가)
//        friendsService.addFriends(member1, member2);
//    }
//}
