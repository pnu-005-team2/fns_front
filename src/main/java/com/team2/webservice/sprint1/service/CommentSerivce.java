package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.controller.CommentController;
import com.team2.webservice.sprint1.jpa.CommentRepository;
import com.team2.webservice.sprint1.vo.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface CommentSerivce {

    Comment saveComment(Comment comment, int uid);
    boolean deleteComment(int cid);

}
