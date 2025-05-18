package apptive.homework.controller;

import apptive.homework.domain.Member;
import apptive.homework.dto.MemberDto;
import apptive.homework.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/users")
    public ResponseEntity<String> addMember(@RequestBody MemberDto memberDto) {
        memberService.save(memberDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("회원가입이 완료되었습니다.");
    }
}
