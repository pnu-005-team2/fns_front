package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.Post;
import com.team2.webservice.sprint1.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/post")
    public String write(Model model)
    {
        Post client = new Post((long) 1, "test", "Lee", ",,,,", "부산대");
        postRepository.save(client);
        long id = 1;
        List<Post> test = postRepository.findAll();
        System.out.println("TEST : " + test.get(0).getWriter());
        return "test";
    }
}

