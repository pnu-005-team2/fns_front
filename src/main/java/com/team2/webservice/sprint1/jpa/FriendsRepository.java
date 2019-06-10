package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    List<Friends> findByMyuid(int myuid);
    List<Friends> findByYouruid(int youruid);
} // 인터페이스를 생성후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
