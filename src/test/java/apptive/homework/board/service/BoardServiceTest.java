package apptive.homework.board.service;

import apptive.homework.board.dto.BoardCreateDto;
import apptive.homework.board.dto.BoardDeleteDto;
import apptive.homework.board.dto.BoardResponseDto;
import apptive.homework.board.dto.BoardUpdateDto;
import apptive.homework.board.repository.BoardRepository;
import apptive.homework.domain.Board;
import apptive.homework.member.dto.MemberCreateDto;
import apptive.homework.member.dto.MemberResponseDto;
import apptive.homework.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    void save() {
        MemberCreateDto memberCreateDto = new MemberCreateDto("test@example.com", "test", "password123", "password123");
        MemberResponseDto savedMember = memberService.save(memberCreateDto);

        BoardCreateDto boardCreateDto = new BoardCreateDto("test@example.com", "password123", "테스트테스트테스트테", "내용내", "이름이");
        BoardResponseDto savedBoard = boardService.save(boardCreateDto);

        em.flush();
        em.clear();

        Board findBoard = boardRepository.findById(savedBoard.getId()).get();

        Assertions.assertThat(savedBoard.getId()).isEqualTo(findBoard.getId());
        Assertions.assertThat(savedBoard.getTitle()).isEqualTo(findBoard.getTitle());
        Assertions.assertThat(savedBoard.getContent()).isEqualTo(findBoard.getContent());
        Assertions.assertThat(savedBoard.getName()).isEqualTo(findBoard.getName());
    }

    @Test
    void update() {
        // given
        MemberResponseDto member = memberService.save(new MemberCreateDto("user@example.com", "user", "password123", "password123"));
        BoardResponseDto saved = boardService.save(new BoardCreateDto("user@example.com", "password123", "제목1234567890", "내용", "작성자"));

        // when
        BoardResponseDto updated = boardService.update(saved.getId(),
                new BoardUpdateDto("user@example.com", "password123", "수정제목123456", "수정내용", "수정이름"));

        em.flush();
        em.clear();

        // then
        Assertions.assertThat(updated.getTitle()).isEqualTo("수정제목123456");
        Assertions.assertThat(updated.getContent()).isEqualTo("수정내용");
        Assertions.assertThat(updated.getName()).isEqualTo("수정이름");
    }

    @Test
    void getBoard() {
        //given
        MemberResponseDto member = memberService.save(new MemberCreateDto("user@example.com", "user", "password123", "password123"));
        BoardResponseDto saved = boardService.save(new BoardCreateDto("user@example.com", "password123", "테스트제목1234", "테스트내용", "테스트이름"));

        em.flush();
        em.clear();

        // when
        BoardResponseDto find = boardService.getBoard(saved.getId());

        // then
        Assertions.assertThat(find.getTitle()).isEqualTo(saved.getTitle());
        Assertions.assertThat(find.getContent()).isEqualTo(saved.getContent());
        Assertions.assertThat(find.getName()).isEqualTo(saved.getName());
    }

    @Test
    void delete() {
        // given
        MemberResponseDto member = memberService.save(new MemberCreateDto("user@example.com", "user", "password123", "password123"));
        BoardResponseDto saved = boardService.save(new BoardCreateDto("user@example.com", "password123", "삭제테스트제목1234", "삭제테스트내용", "삭제이름"));

        em.flush();
        em.clear();

        // when
        boardService.delete(saved.getId(),new BoardDeleteDto("user@example.com", "password123"));

        // then
        boolean exists = boardRepository.findById(saved.getId()).isPresent();
        Assertions.assertThat(exists).isFalse();
    }
}