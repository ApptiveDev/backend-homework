package apptive.homework.repository;

import apptive.homework.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired BoardRepository repo;

    @Test
    void saveAndFindById() {
        Board b = Board.builder()
                .title("0123456789")
                .content("내용")
                .name("익명")
                .memberId(1L)
                .build();
        var saved = repo.save(b);
        var opt   = repo.findById(saved.getBoardId());
        assertThat(opt).isPresent()
                .get()
                .extracting(Board::getTitle)
                .isEqualTo("0123456789");
    }
}
