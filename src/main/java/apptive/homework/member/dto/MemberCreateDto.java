package apptive.homework.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberCreateDto {

    @NotBlank
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank
    @Size(min=3, message = "이름은 3자리 이상이어야 합니다.")
    private String name;

    @NotBlank
    @Size(min=10, message = "비밀번호는 최소 10자리 이상이어야 합니다.")
    private String password;

    @NotBlank
    private String confirmPassword;

    protected MemberCreateDto() {}

    public MemberCreateDto(String email, String name, String password, String confirmPassword) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
