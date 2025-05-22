package apptive.basic.service;

import apptive.basic.user.dto.UserSignupRequestDto;
import apptive.basic.user.entity.User;
import apptive.basic.user.repository.UserRepository;
import apptive.basic.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signup_success() {

        UserSignupRequestDto request = new UserSignupRequestDto(
                "test123@example.com", "테스터", "password1234", "password1234"
        );

        userService.signup(request);

        User user = userRepository.findByEmail("test123@example.com").orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("테스터");
    }

    @Test
    @DisplayName("비밀번호 불일치 시 회원가입 실패")
    void signup_fail_by_password_mismatch() {

        UserSignupRequestDto request = new UserSignupRequestDto(
                "test456@example.com", "테스터", "password1234", "password5678"
        );

        assertThatThrownBy(() -> userService.signup(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
    }
}
