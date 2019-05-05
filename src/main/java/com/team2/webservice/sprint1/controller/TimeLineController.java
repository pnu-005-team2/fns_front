package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.vo.Post;
import com.team2.webservice.sprint1.jpa.PostRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.util.List;

@Controller
public class TimeLineController {
    @Autowired
    private PostRepository postRepository;


    List<Post> postRecordList;


   /* @GetMapping
    @RequestMapping("/post")
    public String write(Model model)
    {

        return "Post";
    }*/

    @PostMapping
    @RequestMapping("/timeline")
    public String Post(ModelMap modelMap)
    {
//        String content =request.getParameter("content");
 //       Post client = new Post();



        postRecordList = postRepository.findAll();
        for (int i = 0; i < postRecordList.size(); i++) {
            System.out.printf("pot : " + postRecordList.get(i).getMember().getEmail());
        }
//       int byte_num=postRecordList.size();


/*
ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buf = new byte[1024];
    Blob blob = profile.getContent();
    InputStream in =  blob.getBinaryStream();
    System.out.println("id content" +in);
    int n = 0;
    while ((n=in.read(buf))>=0)
    {
        baos.write(buf, 0, n);

    }

    in.close();
    byte[] bytes = baos.toByteArray();
    System.out.println("bytes" +bytes);
    byte[] encodeBase64 = Base64.encodeBase64(buf);
    String base64Encoded = new String(encodeBase64, "UTF-8");


    customer.setEmailId(customerName);
    profile.setCustomer(customer);
    //profile.setContent(blob);
    System.out.println();
    profile = profileService.findProfileById(customer);
    model.addAttribute("content",base64Encoded);
    model.addAttribute("profile", profile);
    return "myProfile";
 */

     /*   for(int i=0;i<postRecordList.size();i++){

            try{
                byte[] imgByteArray= postRecordList.get(i).getImg().getBytes();

                //byte[] imgByteArray = Base64.getDecoder().decode(temp_img_String.getBytes());

                postresults_List.get(i).content_result= postRecordList.get(i).getContent();
                postresults_List.get(i).hashtag_result= postRecordList.get(i).getHashtag();
                postresults_List.get(i).pid_result= postRecordList.get(i).getPid();
                postresults_List.get(i).writer_result= postRecordList.get(i).getWriter();
                postresults_List.get(i).img_result= imgByteArray;
            }catch (Exception ex){
                ex.printStackTrace();
            }

//
        }*/

      Post post;


        //List<Image>


        modelMap.addAttribute("postRecordlList",postRecordList);
        modelMap.addAttribute("postRecordList_Byte",postRecordList);
      //  model.addAttribute("postResult_ArrayList",postresults_List);
       // model.addAttribute("postImageList",)

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

    @RequestMapping("/logoShowForStudent/{pid}")
    public void imageView(HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int pid) throws IOException {
        res.setContentType("image/png");



        try{
            Blob blob = postRecordList.get(pid).getImg();
            int blob_length =(int)blob.length();
            try{
                byte[] imagefile1 =postRecordList.get(pid).getImg().getBytes(1,blob_length);
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

