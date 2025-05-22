package apptive.homework.service;

import apptive.homework.domain.Board;
import apptive.homework.domain.Member;
import apptive.homework.dto.BoardDto;
import apptive.homework.repository.BoardRepository;
import apptive.homework.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setup() {

        boardRepository.deleteAll();

        member = new Member();
        member.setEmail("test@naver.com");
        member.setPassword("securepass1234");
        member.setName("홍길동");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("게시글 작성 성공")
    void write_success() {
        BoardDto dto = new BoardDto();
        dto.setEmail("test@naver.com");
        dto.setPassword("securepass1234");
        dto.setTitle("제목이열글자이상이다");
        dto.setContent("내용내용내용");
        dto.setName("익명1");

        boardService.write(dto);

        Board board = boardRepository.findAll().get(0);
        assertThat(board.getTitle()).isEqualTo("제목이열글자이상이다");
        assertThat(board.getName()).isEqualTo("익명1");
    }

    @Test
    @DisplayName("제목 길이 부족으로 실패")
    void write_fail_title_short() {
        BoardDto dto = new BoardDto();
        dto.setEmail("test@naver.com");
        dto.setPassword("securepass1234");
        dto.setTitle("짧다"); // < 10자
        dto.setContent("내용내용");
        dto.setName("익명1");

        assertThrows(IllegalArgumentException.class, () -> boardService.write(dto));
    }

    @Test
    @DisplayName("비밀번호 불일치로 실패")
    void write_fail_wrong_password() {
        BoardDto dto = new BoardDto();
        dto.setEmail("test@naver.com");
        dto.setPassword("틀린비번");
        dto.setTitle("제목이열글자이상이다");
        dto.setContent("내용내용");
        dto.setName("익명1");

        assertThrows(IllegalArgumentException.class, () -> boardService.write(dto));
    }

    @Test
    @DisplayName("존재하지 않는 이메일로 실패")
    void write_fail_email_not_found() {
        BoardDto dto = new BoardDto();
        dto.setEmail("notfound@naver.com");
        dto.setPassword("password123");
        dto.setTitle("제목이열글자이상이다");
        dto.setContent("내용내용");
        dto.setName("익명1");

        assertThrows(IllegalArgumentException.class, () -> boardService.write(dto));
    }

    @Test
    @DisplayName("익명 이름 짧아서 실패")
    void write_fail_name_too_short() {
        BoardDto dto = new BoardDto();
        dto.setEmail("test@naver.com");
        dto.setPassword("securepass1234");
        dto.setTitle("제목이열글자이상이다");
        dto.setContent("내용내용");
        dto.setName("김"); // 1자

        assertThrows(IllegalArgumentException.class, () -> boardService.write(dto));
    }
}
