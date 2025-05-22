package apptive.homework.service;

import apptive.homework.domain.Member;
import apptive.homework.dto.MemberDto;
import apptive.homework.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll(); // 테스트 간 데이터 간섭 방지. 내장메서드 이용
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void join_success() {
        // given: 정상적인 회원 정보
        MemberDto dto = new MemberDto();
        dto.setEmail("test@example.com");
        dto.setName("홍길동");
        dto.setPassword("1234567890");
        dto.setConfirmPassword("1234567890");

        // when: 회원가입 실행
        memberService.join(dto);

        // then: 저장된 회원 정보 확인
        Member saved = memberRepository.findByEmail("test@example.com").orElse(null);
        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("홍길동");

        // Assertions.assert~ 메서드를 쓰면 아래 창에서 초록/주황 체크표시로 결과 확인 가능
    }

    @Test
    @DisplayName("이메일 중복 시 가입 실패")
    void join_duplicate_email_fail() {
        // given: 같은 이메일을 가진 두 회원 정보
        MemberDto dto1 = new MemberDto();
        dto1.setEmail("test@example.com");
        dto1.setName("김길동");
        dto1.setPassword("1234567890");
        dto1.setConfirmPassword("1234567890");

        MemberDto dto2 = new MemberDto();
        dto2.setEmail("test@example.com");
        dto2.setName("이길동");
        dto2.setPassword("1234567890");
        dto2.setConfirmPassword("1234567890");

        // when: 첫 번째 회원가입 성공 후
        memberService.join(dto1);

        // then: 두 번째는 예외 발생
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto2));

        // assertThrows(예외클래스.class, () -> 실행할코드);
        // 예외 발생을 기대하는 테스트 코드
    }

    @Test
    @DisplayName("비밀번호 불일치 시 가입 실패")
    void join_password_mismatch_fail() {
        // given: password와 confirmPassword가 다름
        MemberDto dto = new MemberDto();
        dto.setEmail("new@example.com");
        dto.setName("최길동");
        dto.setPassword("1234567890");
        dto.setConfirmPassword("different123");

        // then: 예외 발생
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }

    @Test
    @DisplayName("이메일 형식 오류 시 가입 실패")
    void join_invalid_email_fail() {
        // given: 잘못된 이메일 형식
        MemberDto dto = new MemberDto();
        dto.setEmail("invalid-email");
        dto.setName("한길동");
        dto.setPassword("1234567890");
        dto.setConfirmPassword("1234567890");

        // then: 예외 발생
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }

    @Test
    @DisplayName("이름 길이 부족 시 가입 실패")
    void join_fail_short_name() {
        // given: 이름이 3자 미만
        MemberDto dto = new MemberDto();
        dto.setEmail("shortname@example.com");
        dto.setName("홍");  // 1자
        dto.setPassword("1234567890");
        dto.setConfirmPassword("1234567890");

        // then: 예외 발생
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }

    @Test
    @DisplayName("비밀번호 길이 부족 시 가입 실패")
    void join_fail_short_password() {
        // given: 비밀번호가 10자 미만
        MemberDto dto = new MemberDto();
        dto.setEmail("shortpw@example.com");
        dto.setName("홍길동");
        dto.setPassword("short");
        dto.setConfirmPassword("short");

        // then: 예외 발생
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }
}
