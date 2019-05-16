package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
