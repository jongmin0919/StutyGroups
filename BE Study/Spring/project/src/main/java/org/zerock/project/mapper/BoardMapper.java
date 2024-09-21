package org.zerock.project.mapper;

import org.zerock.project.domain.BoardVO;
import org.zerock.project.domain.Criteria;

import java.util.List;

public interface BoardMapper {
    List<BoardVO> getList();
    List<BoardVO> getPage(Criteria criteria);

    int insert(BoardVO boardVO);

    int update(BoardVO boardVO);

    int delete(Long bno);

    BoardVO select(Long bno);

}
