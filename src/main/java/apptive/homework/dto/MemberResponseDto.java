package apptive.homework.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String email;
    private String name;
    private String password;

    protected MemberResponseDto(){}
    public MemberResponseDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
