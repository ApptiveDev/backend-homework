package apptive.basic.todo.dto;

import lombok.Getter;

@Getter
public class ToDoResponseDto {

    private Long id;
    private String title;
    private String content;

    public ToDoResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
