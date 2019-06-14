package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.LikeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRecordRepository extends JpaRepository<LikeRecord, Long> {


    Optional<LikeRecord> findByBoard(Board board);
} // 인터페이스를 생성후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
