package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.controller.CommentController;
import com.team2.webservice.sprint1.jpa.CommentRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Comment;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentSerivce {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    private static final Logger logger =LoggerFactory.getLogger(CommentServiceImpl.class);


    @Override
    public Comment saveComment(Comment comment, int uid) {
        logger.info("Save Comment");
        comment.setCreated_date(LocalDateTime.now());
        //Todo 이렇게 꼭 찾아줘야하나? 방법이 있을거 같은데....
        Optional<Member> member = memberRepository.findById(uid);

        if(!member.isPresent()){
            logger.error("글쓴이가 올바르지 않습니다.");
            return null;
        }

        comment.setMember(member.get());

        return commentRepository.save(comment);
    }

    public boolean deleteComment(int cid){
        Optional<Comment> comment = commentRepository.findById(cid);
        if(!comment.isPresent()){
            logger.info("삭제할 댓글이 올바르지 않습니다.");
            return false;
        }
        commentRepository.delete(comment.get());
        return true;
    }
}
