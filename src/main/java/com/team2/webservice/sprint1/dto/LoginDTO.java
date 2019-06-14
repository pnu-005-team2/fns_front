package com.team2.webservice.sprint1.dto;

import lombok.Data;


/*
   VO(Value Object)는 DB와 밀접하다. 즉 DB의 도메인정보를 클라이언트에게 전달 할 때, 그 도메인단위 정보를 VO로 구현하여 사용한다.
   DTO(Data Transfer Object)는 VO보다 협소한 영역에서 사용, DB의 도메인과 일치하지 않으며, 어떤 로직에 사용되는 영속적이지 않는 데이터를 포함하는
   클래스를 DTO로 만틀어서 사용한다. DTO는 뷰에서 컨트롤러 일방향적으로 사용된다.
 */

@Data
public class LoginDTO {

    private String memberEmail;
    private String memberPw;
    private boolean memberCookie;

}
