// src/test/java/apptive/homework/HomeworkApplicationTests.java
package apptive.homework;

import apptive.homework.dto.MemberSignupRequest;
import apptive.homework.dto.BoardRequest;
import apptive.homework.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HomeworkApplicationTests {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		// 스프링 컨텍스트가 정상 로드되는지 확인
	}

	@Test
	void 회원가입_성공() {
		// given
		var req = MemberSignupRequest.builder()
				.email("user@example.com")
				.name("홍길동")
				.password("password1234")
				.confirmPassword("password1234")
				.build();
		// when
		Long id = memberService.register(req);
		// then
		assertThat(id).isPositive();
	}

	@Test
	void 회원가입_실패_비밀번호불일치() {
		var req = MemberSignupRequest.builder()
				.email("user2@example.com")
				.name("철수")
				.password("password1234")
				.confirmPassword("mismatch")
				.build();
		assertThrows(IllegalArgumentException.class,
				() -> memberService.register(req));
	}

	@Test
	void 회원가입_실패_중복이메일() {
		var req = MemberSignupRequest.builder()
				.email("dup@example.com")
				.name("영희")
				.password("password1234")
				.confirmPassword("password1234")
				.build();
		memberService.register(req);
		assertThrows(IllegalArgumentException.class,
				() -> memberService.register(req));
	}

	@Test
	void 게시글작성_실패_인증없음() throws Exception {
		// 가입되지 않은 user로 요청
		var req = BoardRequest.builder()
				.title("0123456789")
				.content("내용입니다.")
				.name("익명")
				.email("noone@example.com")
				.password("wrongpw")
				.build();

		mockMvc.perform(post("/boards")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isBadRequest());
	}
}
