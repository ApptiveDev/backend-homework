package apptive.homework.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequest {

    @Email
    @NotBlank(message = "이메일은 필수임.")
    private String email;

    @NotBlank(message = "비번은 필수임.")
    private String password;

    @NotBlank (message = "제목은 필수임.")
    @Size(min = 10, max = 20, message = "제목은 10~20글자 사이여야 함.")
    private String title;

    @NotBlank (message = "내용은 필수임.")
    @Size(min = 3, message = "내용은 3글자 이상이여야 함.")
    private String content;

    @NotBlank (message = "이름은 필수임.")
    @Size(min = 3, message = "이름은 3글자 이상이어야 함.")
    private String name;

}