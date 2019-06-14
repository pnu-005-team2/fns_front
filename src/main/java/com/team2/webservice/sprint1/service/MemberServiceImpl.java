package com.team2.webservice.sprint1.service;

import com.team2.webservice.common.S3Uploader;
import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.dto.MyPageDTO;
import com.team2.webservice.sprint1.dto.ProfileDTO;
import com.team2.webservice.sprint1.jpa.BoardRepository;
import com.team2.webservice.sprint1.jpa.FriendsRepository;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service // 현재 클래스를 스프링에서 관리하는 service bean으로 등록
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @Autowired
    S3Uploader s3Uploader;


    @Override
    public boolean register(Member member){
        Optional<Member> dupliMember = memberRepository.findByEmail(member.getEmail());
        if(dupliMember.isPresent()) {
            System.out.println("중복된 회원입니다.");
            return false;
        }
        memberRepository.save(member);
        System.out.println(member.getEmail() + " 회원등록 완료");
        return true;
    }

    @Override
    public Optional<Member> login(LoginDTO loginDTO){
        System.out.println(loginDTO.getMemberEmail());
        System.out.println(loginDTO.getMemberPw());
        Optional<Member> oMember = memberRepository.findByEmailAndPassword(
                loginDTO.getMemberEmail(), loginDTO.getMemberPw());

        return oMember;
    }

    @Override
    public Optional<Member> editProfile(ProfileDTO profileDTO) throws IOException {
        Optional<Member> oMember = memberRepository.findByEmail(profileDTO.getEmail());
        Member member = null;
        logger.info(profileDTO.toString());

        if(oMember.isPresent()){
            member = oMember.get();
            logger.info("Edit Find");
            if(!profileDTO.getName().equals(""))
                member.setName(profileDTO.getName());
            if(!profileDTO.getIntro().equals(""))
                member.setIntro(profileDTO.getIntro());
            if(profileDTO.getImg() != null ) {
                String url = s3Uploader.upload(profileDTO.getImg(), "static");
                System.out.println(url);
                member.setImg(url);
            }
            memberRepository.save(member);
            logger.info("Edit Complete");
        } else{
            logger.error("존재하지 않는 이메일입니다.");
        }
        if(member!=null){
            logger.info(member.getImg());
        }
        return Optional.of(member);
    }

    public List<Board> loadMyBoards(String email){
        logger.info("Entry Load My Boards");

        Optional<List<Board>> boards = boardRepository.findByMemberId(email);

        if(!boards.isPresent()){
            logger.error("게시글이 존재하지 않습니다.");
            return null;
        }

        for (int i = 0; i < boards.get().size(); i++) {
            System.out.println(boards.get().get(i).getContent());
        }

        return boards.get();
    }

    public MyPageDTO transDTO(Member member){
        MyPageDTO myPageDTO = new MyPageDTO(member);
        int following_cnt = friendsRepository.findByMyuid(member.getUid()).size();
        int follower_cnt = friendsRepository.findByYouruid(member.getUid()).size();
        Optional<List<Board>> boards = boardRepository.findByMemberId(member.getEmail());
        int board_cnt = 0;
        if(boards.isPresent()) board_cnt = boards.get().size();
        myPageDTO.setFollower_cnt(follower_cnt);
        myPageDTO.setFollowing_cnt(following_cnt);
        myPageDTO.setBoard_cnt(board_cnt);
        return myPageDTO;
    }

}
