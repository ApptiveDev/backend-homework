package apptive.homework.validator;

import apptive.homework.dto.MemberDto;
import apptive.homework.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 이메일 중복 확인 + 형식 검증
    public void validateEmail(MemberDto dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        if (!dto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }

    // 이름 길이 검증 (3자 이상)
    public void validateName(MemberDto dto) {
        if (dto.getName().length() < 3) {
            throw new IllegalArgumentException("이름은 최소 3자 이상이어야 합니다.");
        }
    }

    // 비밀번호 확인 일치 여부
    public void validatePasswordMatch(MemberDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호와 확인이 일치하지 않습니다.");
        }
    }

    // 비밀번호 길이 검증 (10자 이상)
    public void validatePasswordLength(MemberDto dto) {
        if (dto.getPassword().length() < 10) {
            throw new IllegalArgumentException("비밀번호는 10자 이상이어야 합니다.");
        }
    }

    // 통합 유효성 검사
    public void validateAll(MemberDto dto) {
        validateEmail(dto);
        validateName(dto);
        validatePasswordMatch(dto);
        validatePasswordLength(dto);
    }
}
