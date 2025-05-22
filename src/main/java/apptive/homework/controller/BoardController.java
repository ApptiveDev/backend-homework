package apptive.homework.controller;

import apptive.homework.domain.Board;
import apptive.homework.dto.BoardDto;
import apptive.homework.service.BoardService;
import apptive.homework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public void createBoard(@RequestBody BoardDto boardDto) {
        boardService.write(boardDto);
    }

    @GetMapping("/boards/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/boards/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boardService.update(id, boardDto);
    }

    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boardService.delete(id, boardDto);
    }

}
