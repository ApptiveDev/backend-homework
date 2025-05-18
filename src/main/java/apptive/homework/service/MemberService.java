package apptive.homework.service;


import apptive.homework.domain.Member;
import apptive.homework.dto.MemberDto;
import apptive.homework.exception.InvalidInputException;
import apptive.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {
        if (!memberDto.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidInputException("이메일 형식이 올바르지 않습니다.");
        }

        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new InvalidInputException("이미 사용 중인 이메일입니다.");
        }

        if (memberDto.getName().length() < 3) {
            throw new InvalidInputException("이름은 최소 3자 이상이어야 합니다.");
        }

        if (memberDto.getPassword().length() < 10) {
            throw new InvalidInputException("비밀번호는 최소 10자 이상이어야 합니다.");
        }

        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())) {
            throw new InvalidInputException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Member member = new Member(memberDto.getEmail(), memberDto.getName(), memberDto.getPassword());
        memberRepository.save(member);
    }
}
