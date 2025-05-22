package apptive.homework.controller;

import apptive.homework.dto.MemberDto;
import apptive.homework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public String joinMember(@RequestBody MemberDto dto) {
        try {
            memberService.join(dto);
            return "회원가입 성공";
        } catch (IllegalArgumentException e) {
            return "회원가입 실패: " + e.getMessage();
        }
    }
}
