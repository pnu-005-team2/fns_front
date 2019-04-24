package com.team2.webservice.sprint1.jpa;
<<<<<<< HEAD
=======

>>>>>>> 84b175acf1703fe6845c3c585be3745ca443dde8
import com.team2.webservice.sprint1.dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
} // 인터페이스를 생성후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
