package apptive.homework.controller;

import apptive.homework.dto.MemberSignupRequest;
import apptive.homework.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> signup(
            @Valid @RequestBody MemberSignupRequest req) {
        Long id = memberService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("memberId", id));
    }
}