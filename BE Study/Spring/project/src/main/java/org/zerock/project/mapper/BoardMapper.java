package org.zerock.project.mapper;

import org.zerock.project.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
    List<BoardVO> getList();

    int insert(BoardVO boardVO);

    int update(BoardVO boardVO);

    int delete(Long bno);

    BoardVO select(Long bno);

}
