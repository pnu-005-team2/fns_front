package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Controller
public class TimeLineController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FriendsServiceImpl friendsService;


    List<Board> boardRecordList;


    @RequestMapping(value = "/timeline/origin", method = RequestMethod.POST)
    public String write(Model model)
    {
        System.out.println("Origin");
        boardRecordList = boardRepository.findAll();

        model.addAttribute("postRecordlList", boardRecordList);
        model.addAttribute("postRecordList_Byte", boardRecordList);
        return "Timeline";

    }

    @PostMapping
    @RequestMapping("/timeline")
    public String Post(ModelMap modelMap, Model model, HttpServletRequest request)
    {

        //about Friends controlling
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("login"));
        Member me = (Member)session.getAttribute("login");

        //List<Image>
//        boardRecordList = boardRepository.findAll();
        List<Board> fBoardList = friendsService.showFBoard(me);

        modelMap.addAttribute("postRecordlList", fBoardList);
        modelMap.addAttribute("postRecordList_Byte", fBoardList);


        //show following
        List<Member> Friends = friendsService.showFriends(me);

        model.addAttribute("friendsRecordList", Friends);
        model.addAttribute("friendsRecordList_Byte", Friends);

        //show follower
        List<Member> Friended = friendsService.showFriended(me);

        model.addAttribute("friendedRecordList", Friended);
        model.addAttribute("friendedRecordList_Byte", Friended);

        //show recomend
        List<Member> recommend = friendsService.recommendFriends(me);

        model.addAttribute("friendRecommendList", recommend);
        model.addAttribute("friendRecommendList_Byte", recommend);


        //확인
//        System.out.println(Friends.size());
//        for(int i = 0 ; i < Friends.size() ; i++){
//            System.out.println(Friends.get(i).getName());
//        }
//        for(int i = 0; i< Friended.size() ; i++){
//            System.out.println(Friended.get(i).getName());
//        }

        return "timeLineVer2";
    }

    @RequestMapping(value = "/friendAdd" , method = RequestMethod.POST)
    public void friendAdd(int uid1, int uid2){
        //1: 추가행위를 하는사람(following 에 추가) 2:추가행위를 당하는사람(follower에 추가)
        System.out.println("##### Entry Friend Add #######");
        System.out.println("uid1 : " + uid1);
        System.out.println("uid2 : " + uid2);
        friendsService.addFriends(uid1, uid2);
    }
    @RequestMapping(value = "/friendDelete" , method = RequestMethod.POST)
    public void friendDelete(int uid1, int uid2){
        friendsService.deleteFriend(uid1, uid2);
    }

    @RequestMapping("/logoShowForStudent/{pid}")
    public void imageView(HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") Long pid) throws IOException {
        res.setContentType("image/png");


        try{
            Optional<Board> oBoard = boardRepository.findById(pid);
            Board board = oBoard.get();
            Blob blob = board.getImg();
            int blob_length =(int)blob.length();
            try{
                byte[] imagefile1 = board.getImg().getBytes(1,blob_length);
                InputStream in1 = new ByteArrayInputStream(imagefile1);
                IOUtils.copy(in1, res.getOutputStream());
            }catch (Exception e){
             e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }




   /* @ResponseBody
    @PostMapping
    @RequestMapping("/like_boolean_check")
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
        return "Timeline";



    }*/


}

/*
 데이터관리
 화면: 인스타그램
 오른쪽에 이름 및 정보
 중간에 타임라인
 타임라인 : 글을 들고와서 띄우기
 어떤 틀에
 이름과
 글을 띄우기

 각 게시물은 작성순으로 배치
 좋아요버튼
 => 좋아요 누르면 좋아요 갯수 플러스
 => DB에 접근한다음 이를 증가시키고 그담 다시 불러와서 그 값을 띄우기

 댓글작성
 =>Comment 되있는 저걸 이용해서 게시물과 함께 저것을 띄운다
 =>Comment -> submit -> Database 에 저장 -> 들고와서 게시물에 보여줌

 => 이것들은 모두 Thread로 구현해야할듯
 => 지속적으로 보여줘야하니까
 => 내일아침에 간단하게 좋아요버튼이랑 버튼이랑 만들어서 사용해보고
 => 무한스크롤
 =>
 */

