package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.Post;
import com.team2.webservice.sprint1.jpa.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TimeLineController {


    @Autowired
    private PostRepository postRepository;

   /* @GetMapping
    @RequestMapping("/post")
    public String write(Model model)
    {

        return "Post";
    }*/

    @PostMapping
    @RequestMapping("/timeline")
    public String Post(Model model)
    {
//        String content =request.getParameter("content");
 //       Post client = new Post();


        List<Post> postRecordList = postRepository.findAll();
        model.addAttribute("postRecordlList",postRecordList);

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

