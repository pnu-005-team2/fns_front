package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @Override
    Optional<Board> findById(Long aLong);

    @Query("select b from Board b where  b.member.email = (:email) ")
    Optional<List<Board>> findByMemberId(@Param("email") String email);
}

