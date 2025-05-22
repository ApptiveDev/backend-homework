package apptive.homework.validator;

import apptive.homework.domain.Member;
import apptive.homework.dto.BoardDto;
import apptive.homework.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class BoardValidator {

    private final MemberRepository memberRepository;

    public BoardValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 보드 title은 10글자 이상 20글자 이하, content, name은 3글자 이상인가
    public void validateContent(BoardDto dto) {
        if (dto.getTitle() == null || dto.getTitle().length() < 10 || dto.getTitle().length() > 20) {
            throw new IllegalArgumentException("제목은 10자 이상 20자 이하이어야 합니다.");
        }
        if (dto.getContent() == null || dto.getContent().length() < 3) {
            throw new IllegalArgumentException("내용은 3자 이상이어야 합니다.");
        }
        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new IllegalArgumentException("익명 이름은 3자 이상이어야 합니다.");
        }
    }

    // 보드에 접근하는 사용자의 이메일과 비밀번호가 유효한가
    public void IsValidAccess(BoardDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


}
