package apptive.homework.member.service;

import apptive.homework.domain.Member;
import apptive.homework.exception.MemberNotFoundException;
import apptive.homework.exception.AuthenticationFailedException;
import apptive.homework.member.dto.MemberCreateDto;
import apptive.homework.member.dto.MemberResponseDto;
import apptive.homework.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto save(MemberCreateDto memberDto) {
        if (memberRepository.findByEmail(memberDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        Member member = new Member(memberDto.getEmail(), memberDto.getPassword(),memberDto.getName());
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember.getId(), savedMember.getEmail(), savedMember.getName());
    }

    public Long validateMember(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(member -> password.equals(member.getPassword()))
                .map(member -> member.getId())
                .orElseThrow(() -> new AuthenticationFailedException());
    }
}
