package com.team2.webservice.sprint1.test;

import com.team2.webservice.sprint1.vo.Member;
import com.team2.webservice.sprint1.jpa.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertTest(){
//        for (int i = 0; i < 10; i++) {
//            Member member = new Member();
//            member.setId("user" + i);
//            member.setPassword("pw"+i);
//            member.setGender("남");
//            MemberRole role = new MemberRole();
//            if(i<= 8) {
//                role.setRoleName("BASIC");
//            } else if(i <= 9){
//                role.setRoleName("MANAGER");
//            } else{
//                role.setRoleName("ADMIN");
//            }
//            member.setRoles(Arrays.asList(role));
//            memberRepository.save(member);
            List<Member> t = memberRepository.findAll();
            System.out.println(t.get(0).getEmail());
            System.out.println(t.get(0).toString());
//        }
    }
}
