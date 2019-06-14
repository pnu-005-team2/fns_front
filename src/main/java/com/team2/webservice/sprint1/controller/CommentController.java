package com.team2.webservice.sprint1.controller;


import com.team2.webservice.sprint1.service.CommentServiceImpl;
import com.team2.webservice.sprint1.vo.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    private static final Logger logger =LoggerFactory.getLogger(CommentController.class);


    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public Comment commentSave(Comment comment, int uid)
    {
        logger.info("Entry Comment");
        Comment rst_comment = commentService.saveComment(comment, uid);
        return rst_comment;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean commentDelete(int cid){
        logger.info("Entry deleteComment");
        return commentService.deleteComment(cid);
    }

    @ResponseBody
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<Comment> commentLoad(int pid, Pageable pageable) {
        logger.info("Entry Comment Load" + pageable);
        return commentService.loadComment(pid, pageable);
    }


}
