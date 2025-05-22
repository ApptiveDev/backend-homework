package apptive.homework.controller;

import apptive.homework.dto.BoardRequest;
import apptive.homework.domain.Board;
import apptive.homework.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> create(@Valid @RequestBody BoardRequest req) {
        Board b = boardService.write(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @GetMapping("/{id}")
    public Board get(@PathVariable Long id) {
        return boardService.get(id);
    }

    @PutMapping("/{id}")
    public Board edit(@PathVariable Long id,
                      @Valid @RequestBody BoardRequest req) {
        return boardService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestParam String email,
                                       @RequestParam String password) {
        boardService.delete(id, email, password);
        return ResponseEntity.noContent().build();
    }
}
