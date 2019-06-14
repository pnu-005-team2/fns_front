package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.BoardDTO;
import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.LikeRecordRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.service.BoardServiceImpl;
import com.team2.webservice.sprint1.service.FriendsServiceImpl;
import com.team2.webservice.sprint1.service.MusinsaService;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.LikeRecord;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Musinsa;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private MusinsaService musinsaService;

    private static final Logger logger =LoggerFactory.getLogger(TimeLineController.class);


    @Autowired
    FriendsServiceImpl friendsService;

    @Autowired
    BoardServiceImpl boardService;

    List<Board> boardRecordList;
    List<LikeRecord> likeRecordList;


    @RequestMapping(value = "/timeline/origin", method = RequestMethod.POST)
    public String write(Model model)
    {
        logger.info("Origin");
        boardRecordList = boardRepository.findAll();
        model.addAttribute("postRecordlList", boardRecordList);
        model.addAttribute("postRecordList_Byte", boardRecordList);
        return "Timeline";

    }

    @PostMapping
    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public String Post(ModelMap modelMap, Model model, HttpServletRequest request)
    {

        logger.info("Entry TimeLine");
        Member me = friendsService.setFriendListToModel(model, request);
        LocalDate today = LocalDate.now();
        List<Musinsa> musinsaList = musinsaService.getMusinsaList(today);
        logger.info(me.getName());
        List<Board> fBoardList = friendsService.showFBoard(me);

        modelMap.addAttribute("postRecordlList", fBoardList);
        modelMap.addAttribute("postRecordList_Byte", fBoardList);
        modelMap.addAttribute("musinsaList", musinsaList);

        logger.info("Exit TimeLine");

        return "timeLineVer2";
    }


    @RequestMapping("/logoShowForStudent/{pid}")
    public void imageView(HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int pid) throws IOException {
        res.setContentType("image/png");


        try{
            Optional<Board> oBoard = boardRepository.findById(pid);
            if(oBoard.isPresent()){
                Board board = oBoard.get();
                Blob blob = board.getImg();
                int blob_length =(int)blob.length();
                try{
                    byte[] imagefile1 = board.getImg().getBytes(1,blob_length);
                    InputStream in1 = new ByteArrayInputStream(imagefile1);
                    IOUtils.copy(in1, res.getOutputStream());
                }catch (Exception e){
                    Writer writer= new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    logger.info(s);

                }
            }

        }catch (Exception e){
            Writer writer= new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String s = writer.toString();
            logger.info(s);
        }


    }


    @ResponseBody
    @PostMapping
    @RequestMapping("/search/load")
    public List<Member> checkSearchName(String keyword)
    {
        logger.info("Entry checkSearchName" + keyword);
        System.out.println("keykeyword : "+keyword);
        List<Member> member_list = memberRepository.findByNameLike(keyword+"%");

        if(!member_list.isEmpty()) {
            logger.info("No Empty");
            for (int i = 0; i < member_list.size(); i++) {
                logger.info(member_list.get(i).getName());
            }
        }
        else
            logger.info("계정이 존재하지 않습니다.");

        logger.info("Exit checkSearchName");
        return member_list;
    }


    @ResponseBody
    @PostMapping
    @RequestMapping("/search/hash")
    public List<BoardDTO> checkhashSearch(String keyword, Pageable pageable)
    {

        logger.info("Entry checkSearchName" + keyword);
        List<Board> temp_board_list = boardRepository.findByHashtagLike("%"+keyword.substring(1)+"%");
        List<BoardDTO> return_board_list = new ArrayList<>();
        if(keyword.substring(0,1).equals("#")){
            if (temp_board_list != null) {
                for (int i = 0; i < temp_board_list.size(); i++) {
                    if (temp_board_list.get(i).getHashtag() != null) {
                        String temp_HashTag = temp_board_list.get(i).getHashtag();
                        String[] temp_HashTag_Array = temp_HashTag.split(",");

                        for (int j = 0; j < temp_HashTag_Array.length; j++) {
                            logger.info(temp_HashTag_Array[j]+ "comparecompare : " +keyword.substring(1));
                            if (temp_HashTag_Array[j].equals(keyword.substring(1))) {
                                BoardDTO boardDTO=  boardService.transDTO(temp_board_list.get(i),pageable);
                                return_board_list.add(boardDTO);
                                logger.info("retur board list add :  "+ boardDTO.getPid());
                                break;
                            }
                        }


                    }

                }

            }


            if(return_board_list.size()==0){
                logger.info("size : 0");
                return null;
            }
            logger.info("list return");
            return return_board_list;
        }
        return null;
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


        if(board_Optional.isPresent()){
            Optional<LikeRecord> likeRecord_Optional= likeRecordRepository.findByBoard(board_Optional.get());
            if(likeRecord_Optional.isPresent()){
                LikeRecord likeRecord = likeRecord_Optional.get();
                Boolean temp_Boolean =Boolean.parseBoolean(like_Value);
                likeRecord.setLike_boolean(temp_Boolean);
                likeRecord.getMember();
                likeRecordRepository.save(likeRecord);
                return "success";
            }
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

