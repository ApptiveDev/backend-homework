package apptive.homework.controller;

import apptive.homework.dto.BoardRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import apptive.homework.service.MemberService;
import apptive.homework.dto.MemberSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper om;
    @Autowired MemberService memberService;

    private String validEmail = "test@ex.com";
    private String validPw    = "password123";

    @BeforeEach
    void setUp() {
        // 테스트용 사용자 미리 등록
        memberService.register(new MemberSignupRequest(
                validEmail, "tester", validPw, validPw));
    }

    @Test
    void writeSuccess() throws Exception {
        var req = new BoardRequest(
                "0123456789", "내용입니다", "익명", validEmail, validPw
        );
        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void writeFail_Unauthenticated() throws Exception {
        var req = new BoardRequest(
                "0123456789", "내용", "익명", "no@user.com","wrongpw"
        );
        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }
}
