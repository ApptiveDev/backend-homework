package apptive.homework.board.controller;

import apptive.homework.board.dto.BoardCreateDto;
import apptive.homework.board.dto.BoardDeleteDto;
import apptive.homework.board.dto.BoardResponseDto;
import apptive.homework.board.dto.BoardUpdateDto;
import apptive.homework.board.service.BoardService;
import apptive.homework.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> saveBoard(@Valid @RequestBody BoardCreateDto boardDto) {
        BoardResponseDto savedBoard = boardService.save(boardDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        BoardResponseDto boardDto = boardService.getBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardDto);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id,@Valid @RequestBody BoardUpdateDto boardDto) {
        BoardResponseDto updateBoard = boardService.update(id, boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoard);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Map<String,String>> deleteBoard(@PathVariable Long id,@Valid @RequestBody BoardDeleteDto boardDto) {
        boardService.delete(id, boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","삭제 성공"));
    }
}
