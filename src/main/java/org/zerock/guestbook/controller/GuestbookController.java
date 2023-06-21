package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.service.GuestbookService;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor // 자동 주입을 위한 annotation
public class GuestbookController {

    private final GuestbookService service; // final 로 선언

/*    @GetMapping({"/", "list"})
    public String list(){
        log.info("list.............");

        return "/guestbook/list";
    }*/

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ // Model : 결과 데이터를 화면에 전달하기 위해 사용
        log.info("list........." + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
