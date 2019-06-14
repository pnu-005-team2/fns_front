package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Integer> {
    Optional<Board> findById(int pid);

    @Query("select b from Board b where  b.member.email = (:email) ")
    Optional<List<Board>> findByMemberId(@Param("email") String email);

    Optional<Board> findByPid(Long pid);
    List<Board> findByHashtagLike(String hashtag);
}

