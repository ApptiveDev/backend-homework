package apptive.basic.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto
{
    private String email;
    private String password;
    private String title;
    private String content;
    private String name;
}
