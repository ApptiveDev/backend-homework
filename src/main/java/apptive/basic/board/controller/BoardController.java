package apptive.basic.board.controller;

import apptive.basic.board.dto.BoardRequestDto;
import apptive.basic.board.dto.BoardResponseDto;
import apptive.basic.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto request)
    {
        BoardResponseDto response = boardService.createPost(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getPost(@PathVariable Long id)
    {
        BoardResponseDto response = boardService.getPost(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updatePost(
            @PathVariable Long id,
            @RequestBody BoardRequestDto request
    ){
        BoardResponseDto response = boardService.updatePost(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @RequestBody BoardRequestDto request
    ){
        boardService.deletePost(id, request);
        return ResponseEntity.noContent().build();
    }
}
