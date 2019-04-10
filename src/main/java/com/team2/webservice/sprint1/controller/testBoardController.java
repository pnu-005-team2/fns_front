/*package com.team2.webservice.sprint1.controller;

import com.team2.webservice.sprint1.dto.testBoardVo;
import com.team2.webservice.sprint1.jpa.testBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class testBoardController {
    @Autowired
    private testBoardMapper testBoardMapper;

    @RequestMapping("/testBoard")
    public String view(Model model)
    {
        System.out.println("In LikeRecord");
        List<testBoardVo> testBoardList = testBoardMapper.findAll(); // select * from LikeRecord
        model.addAttribute("testBoardList", testBoardList);
        System.out.println(testBoardList.get(0).getBno());
        return "testBoardList";
    }
}
*/