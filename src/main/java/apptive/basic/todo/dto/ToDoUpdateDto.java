package apptive.basic.todo.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ToDoUpdateDto {

    @Size(min = 2, max = 10)
    private String title;

    @Size(min = 2, max = 10)
    private String content;

    protected ToDoUpdateDto() {}

    public ToDoUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
