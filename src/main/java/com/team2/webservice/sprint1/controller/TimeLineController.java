package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.vo.Member;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Controller
public class TimeLineController {


    @Autowired
    private LikeRecordRepository likeRecordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;


    List<Board> boardRecordList;
    List<Member> memberList;
    List<LikeRecord> likeRecordList;


    @RequestMapping(value = "/timeline/origin", method = RequestMethod.GET)
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
    public String Post(ModelMap modelMap)
    {

       //List<Image>
        boardRecordList = boardRepository.findAll();
       likeRecordList= likeRecordRepository.findAll();

       System.out.println("-------------------------------------------------검색");
        modelMap.addAttribute("postRecordlList", boardRecordList);
        modelMap.addAttribute("likeRecordList",likeRecordList);
        for (int i = 0; i < likeRecordList.size(); i++) {
            System.out.println(likeRecordList.get(i).getLike_boolean() );
        };
        return "timeLineVer2";
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


/*
    @RequestMapping("/comment_url/{pid}")
    public void Comment_User_Image(HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") Long pid) throws IOException {
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

    }*/









    @ResponseBody
    @PostMapping
    @RequestMapping("/text_Check")
    public String Check_Search_Name(HttpServletRequest request)
    {



        String name_Search= request.getParameter("For_Search_User_Text");

        if(name_Search!=""){
            if(name_Search.substring(0,1).equals("#")){
                String Drop_String ="#";
                System.out.println("pppppppppppppppppppppppppppppppppp");
                if(boardRecordList!=null){
                    for(int i=0;i<boardRecordList.size();i++){
                        if(boardRecordList.get(i).getHashtag()!=null){
                            String temp_HashTag=boardRecordList.get(i).getHashtag();
                            String[] temp_HashTag_Array=temp_HashTag.split(",");
                            System.out.println("888888888888888888888888888888");
                            for(int j=0;j<temp_HashTag_Array.length;j++){
                                System.out.println(temp_HashTag_Array[j]);
                                if(temp_HashTag_Array[j].equals(name_Search.substring(1))){
                                    System.out.println("이게뭐야");
                                    Drop_String+=String.valueOf(boardRecordList.get(i).getPid());
                                    Drop_String+=" ";
                                }
                            }
                            System.out.println("7777777777777777777777777777777");

                        }

                    }
                }

                return Drop_String;

            }
            else{
                Optional<Member> member_o = memberRepository.findByName(name_Search);
                if(member_o.isPresent()){
                    Member member = member_o.get();
                    return member.getImg();
                }
            }
        }

        return "null";
    }

    @ResponseBody
    @PostMapping
    @RequestMapping("/getImage_url")
    public String GetImageUrl_For_Hash(HttpServletRequest request)
    {

        String temp_data= request.getParameter("temp_data");
        Optional<Board> temp__board= boardRepository.findByPid(Long.parseLong(temp_data.trim()));
        if(temp__board.isPresent()){
            return temp__board.get().getMember().toString();
        }

        return "null";


    }



    @ResponseBody
    @PostMapping
    @RequestMapping("/like_btn_Value_Url")
    public String Like_Board_Check_And_Change(HttpServletRequest request)
    {



        String like_Value= request.getParameter("like_Value");
        String post_Pid = request.getParameter("post_Pid");


        Optional<Board> board_Optional = boardRepository.findByPid(Long.parseLong(post_Pid));
        
        //Optional<Member> member_Optional =  memberRepository.findByUid()
       // Optional<LikeRecord> likeRecordsdasdsd = likeRecordRepository.findBy
        Optional<LikeRecord> likeRecord_Optional= likeRecordRepository.findByBoard(board_Optional.get());
        //Optional<Board> board_MemberWriter= boardRepository.findByMember()
        System.out.println(likeRecord_Optional);
        if(likeRecord_Optional.isPresent()){
            LikeRecord likeRecord = likeRecord_Optional.get();
            Boolean temp_Boolean =Boolean.parseBoolean(like_Value);
            likeRecord.setLike_boolean(temp_Boolean);
            likeRecord.getMember();
            likeRecordRepository.save(likeRecord);
            return "success";
        }
        return "null";





    }


    @ResponseBody
    @PostMapping
    @RequestMapping("/likeBoolean_FirstCheck")
    public String Like_Record_BooleanValue_Check(HttpServletRequest request)
    {

        return "success";


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

