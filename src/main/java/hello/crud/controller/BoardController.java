package hello.crud.controller;

import hello.crud.domain.board.Board;
import hello.crud.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/board/list")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public String list(Model model) {
        List<Board> list = boardRepository.findAll();
        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping("/{boardId}")
    public String detail(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/add")
    public String add() {
        return "board/addForm";
    }

    @PostMapping("/add")
    public String addBoard(Board board, RedirectAttributes redirectAttributes) {
        Board saveBoard = boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", saveBoard.getId());
        redirectAttributes.addAttribute("status", true);//파라미터로 추가
        return "redirect:/board/list/{boardId}";
    }

    @PostConstruct
    public void init() {
        boardRepository.save(new Board("제목1", "내용1"));
        boardRepository.save(new Board("제목2", "내용2"));
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute(board);
        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute Board board) {
        boardRepository.update(boardId,board);
        return "redirect:/board/list/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String deleteForm() {
        return "/board/deleteForm";
    }

    @PostMapping("/{boardId}/delete")
    public String delete(@PathVariable Board boardId, @ModelAttribute Board board) {
        boardRepository.delete(boardId);
        return "redirect:/board/list";
    }
}
