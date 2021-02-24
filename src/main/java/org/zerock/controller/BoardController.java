package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("list");
//        model.addAttribute("list", service.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria criteria, Model model) {
        log.info("list " + criteria);
        model.addAttribute("list", service.getList(criteria));
        int total = service.getTotal(criteria);
        log.info("total: " + total);
//        PageDTO pageDTO = new PageDTO(criteria, 123);
        model.addAttribute("pageMaker", new PageDTO(criteria,total));
    }


    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register: " + board);
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get","/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("criteria")Criteria criteria, Model model) {
        log.info("/get or /modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr) {
        log.info("modify: " + board);
        if(service.modify(board)) {
            rttr.addFlashAttribute("result","success");
        }
//        rttr.addAttribute("pageNum", criteria.getPageNum());
//        rttr.addAttribute("amount", criteria.getAmount());
//        rttr.addAttribute("type", criteria.getType());
//        rttr.addAttribute("keyword", criteria.getKeyword());
        return "redirect:/board/list" + criteria.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr) {
        log.info("remove..." + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result","success");
        }
//        rttr.addAttribute("pageNum", criteria.getPageNum());
//        rttr.addAttribute("amount", criteria.getAmount());
//        rttr.addAttribute("type", criteria.getType());
//        rttr.addAttribute("keyword", criteria.getKeyword());
        return "redirect:/board/list" + criteria.getListLink();
    }
}
