package com.team2.webservice.sprint1.service;
import com.team2.webservice.sprint1.vo.Board;
import com.team2.webservice.sprint1.vo.ProductLink;

public interface BoardService {

    //게시물 작성
    public void write(Board board, String writer);
    //게시물 상세보기
    public Board read(Long pid);
    //게시물 수정
    public void update(Board board);
    //게시물 삭제
    public void delete(Long pid);

    //의류 위치태그
    public void tagCloth(ProductLink productLink, Board board, int x, int y, String link, String writer);

}
