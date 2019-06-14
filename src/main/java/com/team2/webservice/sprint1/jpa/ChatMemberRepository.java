package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMemberRepository extends JpaRepository<ChatMember,Integer> {
//    Optional<List<ChatMember>> findByMember(Member member);
    Optional<List<ChatMember>> findByUid(int uid);
    Optional<ChatMember> findByUidAndCid(int uid, int cid);
}
