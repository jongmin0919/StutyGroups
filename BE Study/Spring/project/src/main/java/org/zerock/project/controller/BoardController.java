package org.zerock.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.project.domain.BoardVO;
import org.zerock.project.domain.Criteria;
import org.zerock.project.service.BoardService;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(@ModelAttribute("cri") Criteria criteria, Model model){
        log.info("list....................");
        List<BoardVO> list = boardService.getList(criteria);
        log.info(list);
        model.addAttribute("list", list);
    }

    @GetMapping("/{job}/{bno}")
    public String read(
            @PathVariable("job") String job,
            @PathVariable("bno") Long bno,
            Model model){

        log.info("job : " + job);
        log.info("bno : " + bno);

        if(!(job.equals("read") || job.equals("modify"))){
            throw new RuntimeException("Bad Request Job");
        }

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO : " + boardVO);

        model.addAttribute("vo", boardVO);

        return "/board/" + job;
    }

    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String registerPost(BoardVO boardVO, RedirectAttributes rttr){
        log.info(boardVO);
        Long bno = boardService.register(boardVO);
        rttr.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }

    @PostMapping("/remove/{bno}")
    public String remove(@PathVariable("bno") Long bno, RedirectAttributes rttr){

        BoardVO boardVO = boardService.get(bno);
        boardVO.setBno(bno);
        boardVO.setTitle("해당 글은 삭제된 글입니다.");
        boardVO.setContent("해당 글은 삭제된 글입니다.");

        log.info("boardVO : " + boardVO);

        boardService.modify(boardVO);
        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/read";
    }

    @PostMapping("/modify/{bno}")
    public String modify(@PathVariable("bno") Long bno, BoardVO boardVO){

        boardVO.setBno(bno);

        log.info("boardVO : " + boardVO);

        boardService.modify(boardVO);

        return "redirect:/board/read/"+bno;
    }
}
