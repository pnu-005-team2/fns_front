package com.team2.webservice.sprint1.controller;

import org.apache.commons.lang3.StringUtils;
import com.team2.webservice.sprint1.dto.Post;
import com.team2.webservice.sprint1.jpa.PostRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    @RequestMapping("/post")
    public String write(Model model)
    {

        return "Post";
    }

    @PostMapping
    @RequestMapping("/post_")
    public String Post(HttpServletRequest request, @RequestParam MultipartFile image) throws IOException {

        String content =request.getParameter("content");
        byte[] img = null;
        //해시태그
        Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(content);
        String HashTag = null;
        while(m.find()) {
            if(HashTag == null) {
                HashTag=specialCharacter_replace(m.group());
            }
            else{
                HashTag+=',';
                HashTag+=specialCharacter_replace(m.group());
            }
//            content=StringUtils.removeFirst(content,"\\#([0-9a-zA-Z가-힣]*)");
        }

        //이미지
        if (image !=null){
            img = image.getBytes();
        }

        System.out.println(HashTag);
        Post client = new Post();
        client.setContent(content);
        client.setWriter("KIM");
        client.setHashtag(HashTag);
        client.setImg(img);
        postRepository.save(client);
        List<Post> test = postRepository.findAll();
//        for(int i = 0 ; i < test.size() ; ++i){
//            System.out.println("TEST : " + test.get(i).getContent());
//            System.out.println("TEST : " + test.get(i).getImg());


//        }

        return "PostedTest";
    }

//    @GetMapping
//    @RequestMapping("/post_")
//    public String postSuccessed(HttpServletRequest request, @RequestParam("id") String key HttpServletResponse res){
//
//        return "PostedTest";
//    }

    public String specialCharacter_replace(String str) {
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ",",");
        if(str.length() < 1) {
            return null;
        }
        return str;
    }

}

