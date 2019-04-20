package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.jpa.PostRepository;
import com.team2.webservice.sprint1.service.LikeServiceImpl;
import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class LikeRecordController {

    //기능구현에 대한 로직은 Service 클래스에서 관리합니다.
    //컨트롤러는 오직 view와 정보를 주고받고, Service를 호출하는 역할만 합니다.
    //이렇게 컨트롤러와 서비스를 분리함으로서 유지보수가 쉬워지며 코드가 간결해지고 가독성이 증가합니다.
    //서비스는 인터페이스로 구현해야 할 메소드만 명세하며, 서비스를 구현받는 구현체를 따로 만들어줍니다.
    @Autowired
    private LikeServiceImpl likeService;

    @RequestMapping("/test")
    public String view(Model model)
    {
        return "LikeTest";
    }

    @ResponseBody
    @RequestMapping(value = "/like_btn", method = RequestMethod.POST)
    //인자로 객체를 받으면, 해당 객체에 프로퍼티와 이름이 같은 data를 속성으로 가지는 객체를 만들어 줍니다.
    public String LikeBtn_Event_Ctr(LikeRecord likeRecord, HttpServletRequest httpServletRequest){

        System.out.println("LikeBtn In!");
        System.out.println(likeRecord);
        // uid와 pid는 조인컬럼으로 사용되기 때문에 클래스 내부에는 프로퍼티가 존재하지 않습니다.
        // 따라서 service에서 인자로 넘어온  uid,pid로 각 객체를 찾고 이를 좋아요 객체에다 연결시켜줍니다.
        String memberName = httpServletRequest.getParameter("uid");
        Long likePid = Long.parseLong(httpServletRequest.getParameter("pid"));
        likeService.registerLike(likeRecord, memberName, likePid);

//        String likeBtn_pid= httpServletRequest.getParameter("pid");
//        String likeBtn_uid =httpServletRequest.getParameter("uid");


//        String likeBtn_like_boolean = httpServletRequest.getParameter("like_boolean");


//        likeRecord.setPid(Long.parseLong(likeBtn_pid));
//        likeRecord.setUid(Long.parseLong(likeBtn_uid));
//        likeRecord.setMember();
//        likeRecord.setLike_boolean(Boolean.parseBoolean(likeBtn_like_boolean));

//        System.out.println(likeRecord.getMember().getEmail());
//        likeRecordRepository.save(likeRecord);

        return "Timeline";
    }




    //#.DB에서 게시물 받아옴
    //#.각 게시물의 이름(id), 게시물 내용(글, 이미지), 좋아요 갯수
    //#.뿌려주기 : 접속하는 순간, 지속적으로 데이터를 받으며, 게시를 하는 사람과 frined라면 보이도록
    //#.friend가 아니라면 안뿌려주기
    //#.
}
