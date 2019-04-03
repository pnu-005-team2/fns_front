package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
