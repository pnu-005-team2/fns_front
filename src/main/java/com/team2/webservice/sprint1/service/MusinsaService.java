package com.team2.webservice.sprint1.service;


import com.team2.webservice.sprint1.controller.TimeLineController;
import com.team2.webservice.sprint1.jpa.MusinsaRepository;
import com.team2.webservice.sprint1.vo.Musinsa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MusinsaService {

    @Autowired
    MusinsaRepository musinsaRepository;

    private static final Logger logger =LoggerFactory.getLogger(TimeLineController.class);


    public List<Musinsa> getMusinsaList(LocalDate today){
        logger.info("Entry Musinsa Service");
        logger.info(today.toString());
        //Todo 왜 날짜검색이 안되지.....
//        List<Musinsa> musinsaList = musinsaRepository.findByParsingDate(today);
        List<Musinsa> musinsaList = musinsaRepository.findAll();
        System.out.println(musinsaList.size());
        for (int i = 0; i < musinsaList.size(); i++) {
            logger.info(musinsaList.get(i).getTitle());
        }
        return musinsaList;
    }
}
