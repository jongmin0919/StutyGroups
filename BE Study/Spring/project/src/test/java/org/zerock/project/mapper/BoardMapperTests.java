package org.zerock.project.mappers;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.project.mapper.BoardMapper;
import org.zerock.project.domain.BoardVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

    @Autowired(required = false)
    BoardMapper boardMapper;

    @Test
    public void test1(){
        log.info(boardMapper);
    }

    @Test
    public void testList(){
        boardMapper.getList().forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testInsert(){
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("newTitle");
        boardVO.setContent("newTest");
        boardVO.setWriter("newWriter");
        boardMapper.insert(boardVO);
    }

    @Test
    public void testSelect(){
        Long bno = 5L;
        log.info(boardMapper.select(bno));
    }

    @Test
    public void testUpdate(){
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성된 타이틀");
        boardVO.setContent("새로 작성된 내용");
        boardVO.setBno(10L);
        log.info("update count : " + boardMapper.update(boardVO));
    }

    @Test
    public void testDelete(){
        Long bno = 10L;
        log.info("deleted count : " + boardMapper.delete(bno));
    }
}
