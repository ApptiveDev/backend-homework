package apptive.homework.repository;

import apptive.homework.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository repo;

    @Test
    void saveAndFindByEmail() {
        Member m = Member.builder()
                .email("m@e.com")
                .name("name")
                .password("password123")
                .build();
        repo.save(m);
        var opt = repo.findByEmail("m@e.com");
        assertThat(opt).isPresent()
                .get()
                .extracting(Member::getName)
                .isEqualTo("name");
    }
}
