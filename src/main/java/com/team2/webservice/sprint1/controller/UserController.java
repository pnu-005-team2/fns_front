package com.team2.webservice.sprint1.controller;

import com.team2.webservice.common.S3Uploader;
import com.team2.webservice.sprint1.dto.ProfileDTO;
import com.team2.webservice.sprint1.service.MemberServiceImpl;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/user/edit")
public class UserController {

    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(){
        System.out.println("View");
        return "editprofile";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String editInfo(ProfileDTO profileDTO, Model model,
        @RequestParam MultipartFile image) throws IOException {
        profileDTO.setImg(image);
        Optional<Member> oMember = memberService.editProfile(profileDTO);
        if(oMember.isPresent())
            model.addAttribute("updateUser", oMember.get());
        return "home";
    }

}
