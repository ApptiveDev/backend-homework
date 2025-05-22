package apptive.homework.service;

import apptive.homework.domain.Member;
import apptive.homework.dto.MemberSignupRequest;
import apptive.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repo;

    public Long register(MemberSignupRequest dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일");
        }
        Member m = Member.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
        return repo.save(m).getMemberId();
    }

    public Member authenticate(String email, String password) {
        return repo.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("이메일/비밀번호 불일치"));
    }
}