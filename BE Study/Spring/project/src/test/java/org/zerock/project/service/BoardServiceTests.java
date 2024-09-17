package org.zerock.project.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.project.domain.BoardVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setWriter("Test Writer");

        Long bno = boardService.register(board);
        assertNotNull(bno, "Board number should not be null after registration");
        log.info("Registered board: " + board);
    }

    @Test
    public void testList() {
        List<BoardVO> boards = boardService.list();
        assertNotNull(boards, "Board list should not be null");
        assertTrue(boards.size() > 0, "Board list should contain elements");
        log.info("Board list: " + boards);
    }

    @Test
    public void testGet() {
        Long bno = 1L; // Assuming there is a board with ID 1 for testing purposes
        BoardVO board = boardService.get(bno);
        assertNotNull(board, "Board should not be null for existing ID");
        assertEquals(bno, board.getBno(), "Board ID should match the requested ID");
        log.info("Fetched board: " + board);
    }

    @Test
    public void testModify() {
        Long bno = 1L; // Assuming there is a board with ID 1 for testing purposes
        BoardVO board = new BoardVO();
        board.setBno(bno);
        board.setTitle("Updated Title");
        board.setContent("Updated Content");

        boolean result = boardService.modify(board);
        assertTrue(result, "Board should be updated successfully");
        BoardVO updatedBoard = boardService.get(bno);
        assertEquals("Updated Title", updatedBoard.getTitle(), "Title should be updated");
        assertEquals("Updated Content", updatedBoard.getContent(), "Content should be updated");
        log.info("Modified board: " + updatedBoard);
    }

    @Test
    public void testRemove() {
        Long bno = 5L; // Assuming there is a board with ID 1 for testing purposes
        boolean result = boardService.remove(bno);
        assertTrue(result, "Board should be removed successfully");
        BoardVO removedBoard = boardService.get(bno);
        assertNull(removedBoard, "Removed board should be null");
        log.info("Removed board with ID: " + bno);
    }
}
