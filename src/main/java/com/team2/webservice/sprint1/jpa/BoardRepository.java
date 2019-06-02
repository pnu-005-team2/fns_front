package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByHashtag(String hashtag);
    Optional<Board> findByPid(Long pid);
    Optional<Board> findByMember(String email);
}

