package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.ChatMember;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMemberRepository extends JpaRepository<ChatMember,Integer> {
    Optional<List<ChatMember>> findByMember(Member member);
    Optional<ChatMember> findByMemberAndCid(Member member, int cid);
}
