package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.vo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 현재 클래스를 스프링에서 관리하는 service bean으로 등록
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

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

}
