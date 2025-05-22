package apptive.basic.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ToDoCreateDto {

    @NotBlank(message = "제목은 빈 값일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 빈 값일 수 없습니다.")
    private String content;

    protected ToDoCreateDto(){}

    public ToDoCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
