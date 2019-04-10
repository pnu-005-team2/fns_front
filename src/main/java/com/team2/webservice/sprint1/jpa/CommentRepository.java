package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.dto.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
} // 인터페이스를 생성후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
