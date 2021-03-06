package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.ProfileDTO;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.service.MemberServiceImpl;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger =LoggerFactory.getLogger(UserController.class);


    @Autowired
    MemberServiceImpl memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FriendsServiceImpl friendsService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String view(){
        logger.info("View");
        return "editprofile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editInfo(ProfileDTO profileDTO, Model model,
        @RequestParam MultipartFile image) throws IOException {
        profileDTO.setImg(image);
        Optional<Member> oMember = memberService.editProfile(profileDTO);
        if(oMember.isPresent())
            model.addAttribute("updateUser", oMember.get());
        return "redirect:/timeline";
    }

    @RequestMapping(value = "mypage", method = RequestMethod.GET)
    public String mypage(String email, Model model, HttpServletRequest request){
        logger.info("Entry Mypage : " + email);
        Member login = (Member) request.getSession().getAttribute("login");
        List<Board> boards = memberService.loadMyBoards(email);
        Optional<Member> member = memberRepository.findByEmail(email);
        if(!member.isPresent()) logger.error("계정이 존재하지 않습니다.");
        model.addAttribute("boardList", boards);
        if(member.isPresent()){
            model.addAttribute("pageUser", memberService.transDTO(member.get()));
            model.addAttribute("isFriend", friendsService.isFollowing(login.getUid(), member.get().getName()));
        }
        model.addAttribute("isMine", login.getEmail().equals(email));

        return "personalPage";
    }

}
