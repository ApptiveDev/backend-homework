package apptive.homework.dto;


import lombok.Getter;

@Getter
public class MemberDto {
    private String email;
    private String name;
    private String password;
    private String confirmPassword;

    protected MemberDto() {};
    public MemberDto(String email, String name, String password, String confirmPassword) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
