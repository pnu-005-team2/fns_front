package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    List<Friends> findByMyuid(int myuid);
    List<Friends> findByYouruid(int youruid);
    Optional<Friends> findByMyuidAndYouruid(int myuid, int youruid);
    boolean existsByMyuidAndYourname(int myuid, String name);
    boolean existsByMyuidAndYouruid(int myuid, int yourUid);

    @Query("select f.youruid from Friends f where f.myuid  = (:myuid)")
    List<Object> getYouruidByMyuid(@Param("myuid") int myuid);

    @Query("select f.myuid from Friends f where f.youruid  = (:youruid)")
    List<Object> getMyuidByYouruid(@Param("youruid") int youruid);
} // 인터페이스를 생성후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
