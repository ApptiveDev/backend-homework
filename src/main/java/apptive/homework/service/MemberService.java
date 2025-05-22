package apptive.homework.service;

import apptive.homework.domain.Member;
import apptive.homework.dto.MemberDto;
import apptive.homework.repository.MemberRepository;
import apptive.homework.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;  //얘도 @Component로 빈에 주입했으므로 여기 정의해야 함.
    }

    // 회원 가입
    public void join(MemberDto dto) {
        // 유효성 검사
        memberValidator.validateAll(dto);

        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setName(dto.getName());
        member.setPassword(dto.getPassword());

        memberRepository.save(member);
    }
}
