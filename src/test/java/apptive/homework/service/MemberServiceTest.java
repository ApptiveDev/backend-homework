package apptive.homework.service;

import apptive.homework.dto.MemberSignupRequest;
import apptive.homework.domain.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void registerSuccess() {
        var req = new MemberSignupRequest(
                "a@b.com", "홍길동", "password123","password123");
        Long id = memberService.register(req);
        assertThat(id).isNotNull();
    }

    @Test
    void registerFail_DuplicateEmail() {
        var req = new MemberSignupRequest(
                "a@b.com", "홍길동", "password123","password123");
        memberService.register(req);
        assertThatThrownBy(() -> memberService.register(req))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void authenticateFail_WrongPassword() {
        var req = new MemberSignupRequest(
                "c@d.com", "아무개", "password123","password123");
        memberService.register(req);
        assertThatThrownBy(() -> memberService.authenticate("c@d.com", "wrongpw"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
