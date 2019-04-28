package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Post;
import com.team2.webservice.sprint1.dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}

