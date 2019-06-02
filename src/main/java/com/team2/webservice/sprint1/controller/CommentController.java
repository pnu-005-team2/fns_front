package com.team2.webservice.sprint1.controller;



import com.team2.webservice.sprint1.vo.Comment;
import com.team2.webservice.sprint1.jpa.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;




    @RequestMapping("/comment_print")
    public String view(Model model)
    {
        System.out.println("In Comment");
        List<Comment> commentsRecordList = commentRepository.findAll();
        model.addAttribute("_commentList",commentsRecordList);
        System.out.println(commentsRecordList.get(0).getContent());
        return "Timeline";
    }



    @ResponseBody
    @PostMapping
    @RequestMapping("/comment")
    public String Comment(HttpServletRequest request)
    {
        System.out.printf("In Timeline");


        String comment_content= request.getParameter("comment");
        String comment_pid =request.getParameter("pid");
        String comment_writer= request.getParameter("writer");
        String comment_date = request.getParameter("date");


        System.out.print(comment_content+"\n"+comment_pid+"\n"+comment_writer+"\n"+comment_date+"\n");

        Comment copy_comment = new Comment();
        copy_comment.setContent(comment_content);
        copy_comment.setDate(comment_date);

        copy_comment.setWriter(comment_writer);

        commentRepository.save(copy_comment);





        return "Timeline";

    }



}
