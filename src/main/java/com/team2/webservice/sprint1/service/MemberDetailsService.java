//package com.team2.webservice.sprint1.service;
//
//import com.team2.webservice.sprint1.dto.SecurityMember;
//import com.team2.webservice.sprint1.jpa.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.security.Security;
//import java.util.Optional;
//
//@Service
//public class MemberDetailsService implements UserDetailsService {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return
//                Optional.ofNullable(memberRepository.findOneByEmail(email))
//                .filter(m-> m != null)
//                .map(m-> new SecurityMember(m.get())).get();
//
//    }
//}
