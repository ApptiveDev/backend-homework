package apptive.homework.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BoardUpdateDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Size(min = 10, max = 20)
    private String title;

    @Size(min = 3)
    private String content;

    @Size(min = 3)
    private String name;

    protected BoardUpdateDto() {}

    public BoardUpdateDto(String email, String password, String title, String content, String name) {
        this.email = email;
        this.password = password;
        this.title = title;
        this.content = content;
        this.name = name;
    }
}
