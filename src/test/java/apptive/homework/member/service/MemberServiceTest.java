package apptive.homework.member.service;

import apptive.homework.domain.Member;
import apptive.homework.member.dto.MemberCreateDto;
import apptive.homework.member.dto.MemberResponseDto;
import apptive.homework.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        MemberCreateDto memberCreateDto = new MemberCreateDto("test@example.com", "test", "password123", "password123");
        MemberResponseDto saved = memberService.save(memberCreateDto);
        Member findMember = memberRepository.findById(saved.getId()).get();

        assertThat(saved.getId()).isEqualTo(findMember.getId());
        assertThat(saved.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(saved.getName()).isEqualTo(findMember.getName());
    }
}