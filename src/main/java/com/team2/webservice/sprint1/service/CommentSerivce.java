package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.vo.Comment;

public interface CommentSerivce {

    Comment saveComment(Comment comment, int uid);
    boolean deleteComment(int cid);

}
