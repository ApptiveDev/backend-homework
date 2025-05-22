package apptive.homework.service;

import apptive.homework.dto.BoardRequest;
import apptive.homework.dto.MemberSignupRequest;
import apptive.homework.domain.Board;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired MemberService memberService;
    @Autowired BoardService boardService;

    String email = "x@y.com", pw = "password123";

    @BeforeEach
    void initUser() {
        memberService.register(new MemberSignupRequest(
                email, "user", pw, pw));
    }

    @Test
    void writeAndGetSuccess() {
        Board saved = boardService.write(
                new BoardRequest("0123456789","내용","익명",email,pw));
        Board found = boardService.get(saved.getBoardId());
        assertThat(found.getBoardId()).isEqualTo(saved.getBoardId());
    }

    @Test
    void writeFail_Unauthorized() {
        assertThatThrownBy(() ->
                boardService.write(
                        new BoardRequest("0123456789","내용","익명",
                                "no@user.com","pw")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
