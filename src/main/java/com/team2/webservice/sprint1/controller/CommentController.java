package com.team2.webservice.sprint1.controller;



import com.team2.webservice.sprint1.service.CommentServiceImpl;
import com.team2.webservice.sprint1.vo.Comment;
import com.team2.webservice.sprint1.jpa.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    private static final Logger logger =LoggerFactory.getLogger(CommentController.class);


    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public Comment comment(Comment comment, int uid)
    {
        logger.info("Entry Comment");
        Comment rst_comment = commentService.saveComment(comment, uid);
        return rst_comment;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean commentDelete(int cid){
        logger.info("Entry commentDelete");
        return commentService.deleteComment(cid);
    }



}
