package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
