package apptive.basic.service;

import apptive.basic.board.dto.BoardRequestDto;
import apptive.basic.board.dto.BoardResponseDto;
import apptive.basic.board.service.BoardService;
import apptive.basic.user.dto.UserSignupRequestDto;
import apptive.basic.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    /*
    @BeforeEach
    void setUp() {
        // 테스트 유저 회원가입
        UserSignupRequestDto signupRequest = new UserSignupRequestDto(
                "boardtester2@example.com", "홍길동", "boardpass123", "boardpass123"
        );
        userService.signup(signupRequest);
    }
     */

    @BeforeEach
    void setUp() {
        UserSignupRequestDto signupRequest = new UserSignupRequestDto(
                "boardtester2@example.com", "홍길동", "boardpass123", "boardpass123"
        );
        try {
            userService.signup(signupRequest);
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    @DisplayName("게시글 작성 성공")
    void createPost_success() {

        BoardRequestDto request = new BoardRequestDto();
        request.setEmail("boardtester2@example.com");
        request.setPassword("boardpass123");
        request.setTitle("테스트 게시글 제목입니다.");
        request.setContent("게시글 내용입니다.");
        request.setName("익명사람");

        BoardResponseDto response = boardService.createPost(request);

        assertThat(response.getTitle()).isEqualTo("테스트 게시글 제목입니다.");
        assertThat(response.getContent()).isEqualTo("게시글 내용입니다.");
        assertThat(response.getName()).isEqualTo("익명사람");
    }

    @Test
    @DisplayName("비밀번호 틀릴 경우 게시글 작성 실패")
    void createPost_fail_by_wrong_password() {

        BoardRequestDto request = new BoardRequestDto();
        request.setEmail("boardtester2@example.com");
        request.setPassword("wrongpass123");
        request.setTitle("테스트 게시글 제목입니다.");
        request.setContent("내용입니다.");
        request.setName("익명사람");

        assertThatThrownBy(() -> boardService.createPost(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비밀번호가 일치하지 않습니다.");
    }
}
