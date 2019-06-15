package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByName(String name);
    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<List<Member>> findByUidAndNameLike(int id, String name);
    List<Member> findByUidIn(List<Object> uid);
    boolean existsByEmail(String email);

    List<Member> findByNameLike(String name);

    @Query("select m.id from Member m where m.name in (:names)")
    List<Object> getUidByName(@Param("names") Set<String> names);

}
