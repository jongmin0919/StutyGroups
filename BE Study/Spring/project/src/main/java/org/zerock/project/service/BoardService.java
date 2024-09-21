package org.zerock.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.project.domain.BoardVO;
import org.zerock.project.domain.Criteria;
import org.zerock.project.mapper.BoardMapper;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardService {


    private final BoardMapper boardMapper;

    public List<BoardVO> list(){
        return boardMapper.getList();
    }
    public List<BoardVO> getList(Criteria criteria) {
        return boardMapper.getPage(criteria);
    }

    public Long register(BoardVO boardVO){
        log.info("register board" + boardVO);
        int count = boardMapper.insert(boardVO);
        return boardVO.getBno();
    };

    public BoardVO get(Long bno){
        return boardMapper.select(bno);
    }

    public boolean modify(BoardVO boardVO){
        return boardMapper.update(boardVO) == 1;
    }

    public boolean remove(Long bno){
        return boardMapper.delete(bno) == 1;
    }
}
