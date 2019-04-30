package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.service.BoardServiceImpl;
import com.team2.webservice.sprint1.vo.Post;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

@Controller
@RequestMapping("/board")
public class BoardController {

    //기능구현에 대한 로직은 Service 클래스에서 관리합니다.
    //컨트롤러는 오직 view와 정보를 주고받고, Service를 호출하는 역할만 합니다.
    //이렇게 컨트롤러와 서비스를 분리함으로서 유지보수가 쉬워지며 코드가 간결해지고 가독성이 증가합니다.
    //서비스는 인터페이스로 구현해야 할 메소드만 명세하며, 서비스를 구현받는 구현체를 따로 만들어줍니다.
    @Autowired
    BoardServiceImpl boardService;

    //--------------게시물쓰기 요청시 해당하는 유효성을 검증하고 view 리턴---------------------
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(){
        //Todo 유저유효성 검증 필요
        return "Post";
    }

    //--------------프론트에서 넘어온 게시물 정보를 DB에 저장---------------------
    @RequestMapping(value = "/post_", method = RequestMethod.POST)
    public ModelAndView writePost(ModelAndView mav, Post post, @RequestParam MultipartFile image, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("/");


        System.out.println("Post View In");
        post.setImg(image.getBytes());
        String writer = httpServletRequest.getParameter("writer");
        boardService.write(post, writer);



        mav.addObject("image", new String(Base64.getEncoder().encode(image.getBytes())));

        mav.setViewName("PostedTest");
//        return "PostedTest";
        return mav;
    }

    //---------------프론트에서 받아온 사진 게시물위의 좌표를 DB에 저장-------------------
    @RequestMapping(value = "/tagClothing", method = RequestMethod.POST)
    public String tagClothing(Post post, @RequestParam int location){
        System.out.println("--Clothing tag page--");
//        post.setClothingTag("");
//        boardService.tagCloth(post);

        return "Timeline"; //어디로가야하지,,
    }

}
