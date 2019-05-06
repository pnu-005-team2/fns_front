package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.jpa.ProductLinkRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.ProductLink;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Service 어노테이션을 명시함으로서, 스프링이 자동주입을 할 수 있게 해줍니다.
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductLinkRepository productLinkRepository;

    @Override
    public void write(Board board, String writer) {
        System.out.println("-------------------In Board---------------");
        System.out.println(board);

        Pattern hashtagPattern = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher hashMatch = hashtagPattern.matcher(board.getContent());

        //게시글 내용에서 hash 태그를 찾습니다.
        String hashTag = null;
        while (hashMatch.find()){
            if(hashTag == null){
                hashTag = fillter(hashMatch.group());
            } else {
                hashTag += "," + fillter(hashMatch.group());
            }
        }

        //작성자 엔티티를 찾아서 넣어줍니다.
        //Todo ManyToOne 관계일 때 id에 해당하는 객체를 찾아서 넘겨줘야함, 비효율적 리팩토링 필요
        setMember(board,writer);
        board.setHashtag(hashTag);
//        board.setClothingTag("@ ");
        boardRepository.save(board);
        System.out.println("-------------------Write Complete---------------");


    }

    @Override
    public Board read(Long pid) {
        return null;
    }

    @Override
    public void update(Board board) {

    }

    @Override
    public void delete(Long pid) {

    }

    @Override
    public void tagCloth(ProductLink productLink, Board board, int x, int y, String link, String writer){
        System.out.println("-------------tag clothing-------------");

        productLink.setLinktext(link);
        productLink.setPosition_x(x);
        productLink.setPosition_y(y);

        List<Board> post = boardRepository.findAll();
        int postSize = post.size();

        System.out.println(writer);

        int i;
        for(i = postSize-1 ; i >= 0 && !(post.get(i).getMember().getEmail().equals(writer) ) ; --i)
            ;

        long temp_pid = post.get(i).getPid();

        setPid(productLink, temp_pid);

        productLinkRepository.save(productLink);

        System.out.println("------------completed tag-------------");
    }

    //---------- 특수문자를 ,로 바꿔줍니다.---------------
    protected String fillter(String str) {
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ",",");
        if(str.length() < 1) {
            return null;
        }
        return str;
    }

    //---------- 작성회원 엔티티를 찾아서 넣어줍니다..---------------
    protected Optional<Member> setMember(Board board, String email){
        System.out.println(email);
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            board.setMember(member.get());
        else
            System.out.println("해당 계정이 존재하지 않습니다.");
        return member;
    }

    protected Optional<Board> setPid(ProductLink productLink, long pid){
        Optional<Board> post = boardRepository.findById(pid);
        if(post.isPresent())
            productLink.setBoard(post.get());
        else
            System.out.println("해당 게시물이 존재하지 않습니다.");
        return post;
    }

}
