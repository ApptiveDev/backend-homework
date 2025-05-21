package apptive.homework.service;

import apptive.homework.dto.MemberDto;
import apptive.homework.dto.MemberResponseDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    void save_fail(){
        MemberDto memberDto = new MemberDto("test@gmail.com","test","test","test");
        MemberResponseDto saved = memberService.save(memberDto);
        MemberResponseDto findMember = memberService.findMemberByEmail(saved.getEmail());

        Assertions.assertEquals(saved.getEmail(), findMember.getEmail());
        Assertions.assertEquals(saved.getPassword(), findMember.getPassword());
        Assertions.assertEquals(saved.getName(), findMember.getName());
    }

    @Test
    void save_success(){
        MemberDto memberDto = new MemberDto("test@gmail.com","test","testtest1234","testtest1234");
        MemberResponseDto saved = memberService.save(memberDto);
        MemberResponseDto findMember = memberService.findMemberByEmail(saved.getEmail());

        Assertions.assertEquals(saved.getEmail(), findMember.getEmail());
        Assertions.assertEquals(saved.getPassword(), findMember.getPassword());
        Assertions.assertEquals(saved.getName(), findMember.getName());
    }
}
