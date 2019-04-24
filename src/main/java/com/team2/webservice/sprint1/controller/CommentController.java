package com.team2.webservice.sprint1.controller;



import com.team2.webservice.sprint1.dto.Comment;
import com.team2.webservice.sprint1.jpa.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

  /*  @RequestMapping("/timeline")
    @ResponseBody
    public Map<String,Object> ajax(HttpServletRequest request) throws Exception {
            String firstArg = request.getParameter("test1");
            String secondArg = request.getParameter("test2");

            System.out.println(firstArg + " / "+ secondArg);
            Map<String,Object>  map = new HashMap<String,Object>();
            map.put("test1", firstArg);
            map.put("test2", secondArg);

            return map;
    }*/


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

        copy_comment.setPid(Long.parseLong(comment_pid));
        copy_comment.setWriter(comment_writer);

        commentRepository.save(copy_comment);



        //System.out.printf(rkrkrk);

        //comment.setContent(request.getParameter("comment"));
        //System.out.println("+++"+comment.getContent());
        //String content = httpServletRequest.getParameter("")
        //String content =request.getParameter("content");
        //       Post client = new Post();

        //List<Comment> commentsRecordList = commentRepository.findAll();
        //model.addAttribute("_commentList",commentsRecordList);



        return "Timeline";

        /*

          System.out.println("In LikeRecord");
        List<LikeRecord> likeRecordList = likeRecordRepository.findAll(); // select * from LikeRecord
        model.addAttribute("LikeList", likeRecordList);
        System.out.println(likeRecordList.get(0).getLid());
        return "LikeTest";
         */

//        client.setPid((long)2);
     /*   client.setContent(content);
        client.setWriter("KIM");
        client.setHashtag("안녕, 안녕하세요");
        client.setImg("1234");
//        Post client = new Post((long) 1, content, "Lee", ",,,,", "부산대");
//        postRepository.save(client);
        List<Post> test = postRepository.findAll();
        for(int i = 0 ; i < test.size() ; ++i){
            System.out.println("TEST : " + test.get(i).getContent());
        }
*/

    }



}
