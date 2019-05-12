package com.team2.webservice.sprint1.service;

import com.team2.webservice.common.S3Uploader;
import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.dto.ProfileDTO;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service // 현재 클래스를 스프링에서 관리하는 service bean으로 등록
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    MemberRepository memberRepository;

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
        if(oMember.isPresent()){
            member = oMember.get();
            logger.info("Edit Find");
            if(!profileDTO.getName().equals(""))
                member.setName(profileDTO.getName());
            if(!profileDTO.getIntro().equals(""))
                member.setIntro(profileDTO.getIntro());
            if(profileDTO.getImg() != null) {
                String url = s3Uploader.upload(profileDTO.getImg(), "static");
                System.out.println(url);
                member.setImg(url);
            }
            memberRepository.save(member);
            logger.info("Edit Complete");
        } else{
            logger.error("존재하지 않는 이메일입니다.");
        }
        logger.info(member.getImg());
        return Optional.of(member);
    }
}
