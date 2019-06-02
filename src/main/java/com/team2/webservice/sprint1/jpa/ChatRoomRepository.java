package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Integer> {
}
