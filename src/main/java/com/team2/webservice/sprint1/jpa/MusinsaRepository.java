package com.team2.webservice.sprint1.jpa;

import com.team2.webservice.sprint1.vo.Musinsa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MusinsaRepository extends JpaRepository<Musinsa, Integer> {
    List<Musinsa> findByParsingDate(LocalDate find_day);
}
