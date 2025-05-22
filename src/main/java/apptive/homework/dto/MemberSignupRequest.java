package apptive.homework.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignupRequest {

    @Email
    @NotBlank(message = "이메일은 필수임.")
    private String email;

    @NotBlank(message = "이름은 필수임.")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "비번은 필수임.")
    @Size(min = 10, message = "비번은 최소 10글자 이상이여야 함.")
    private String password;

    @NotBlank(message = "비번 확인은 필수임.")
    @Size(min = 10, message = "확인 비번은 최소 10글자 이상이여야 함.")
    private String confirmPassword;
}