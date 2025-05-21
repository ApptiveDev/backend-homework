package apptive.homework.dto;

import lombok.Getter;

@Getter
public class UserProfileDto {
    private String email;
    private String password;

    protected UserProfileDto() {}

    public UserProfileDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
