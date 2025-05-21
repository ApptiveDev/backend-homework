package apptive.homework.controller;


import apptive.homework.dto.*;
import apptive.homework.service.BoardService;
import apptive.homework.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/boards")
    public ResponseEntity<String> addMember(@RequestBody BoardRequestDto boardRequestDto) {
        Long user_id = memberService.verified(boardRequestDto.getUserProfileDto());
        boardService.save(boardRequestDto.getBoardDto(), user_id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardInfoDto> getBoard(@PathVariable Long id) {
        try{
            BoardInfoDto boardInfoDto = boardService.findBoard(id);
            return ResponseEntity.status(HttpStatus.OK).body(boardInfoDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @RequestBody UserProfileDto userProfileDto) {
        Long user_id = memberService.verified(userProfileDto);
        if(boardService.delete(id)) return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        Long userId = memberService.verified(requestDto.getUserProfileDto());
        boolean updated = boardService.update(id, requestDto.getBoardDto());

        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
