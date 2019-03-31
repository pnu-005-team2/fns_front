package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.Post;
import com.team2.webservice.sprint1.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String Post(HttpServletRequest request)
    {
        String content =request.getParameter("content");
        Post client = new Post();
//        client.setPid((long)2);
        client.setContent(content);
        client.setWriter("KIM");
        client.setHashtag("안녕, 안녕하세요");
        client.setImg("1234");
//        Post client = new Post((long) 1, content, "Lee", ",,,,", "부산대");
        postRepository.save(client);
        List<Post> test = postRepository.findAll();
        for(int i = 0 ; i < test.size() ; ++i){
            System.out.println("TEST : " + test.get(i).getContent());
        }

        return "Post";
    }
}

