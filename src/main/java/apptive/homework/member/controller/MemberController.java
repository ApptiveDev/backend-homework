package apptive.homework.member.controller;

import apptive.homework.member.dto.MemberCreateDto;
import apptive.homework.member.dto.MemberResponseDto;
import apptive.homework.member.service.MemberService;
import jakarta.validation.Valid;
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
    public ResponseEntity<MemberResponseDto> saveMember(@Valid @RequestBody MemberCreateDto memberDto) {
        MemberResponseDto savedMember = memberService.save(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }
}
