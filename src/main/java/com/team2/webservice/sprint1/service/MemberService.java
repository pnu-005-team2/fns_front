package com.team2.webservice.sprint1.service;

import com.team2.webservice.sprint1.dto.LoginDTO;
import com.team2.webservice.sprint1.dto.ProfileDTO;
import com.team2.webservice.sprint1.vo.Member;

import java.util.Optional;

public interface MemberService {


    // 회원가입 처리
    boolean register(Member member) throws Exception;

    // 로그인 처리
    Optional<Member> login(LoginDTO loginDTO) throws Exception;

    // 프로필 수정
    Optional<Member> editProfile(ProfileDTO profileDTO) throws Exception;

}
