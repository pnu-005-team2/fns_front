package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}

