package apptive.homework.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardDeleteDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    protected BoardDeleteDto() {}

    public BoardDeleteDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
