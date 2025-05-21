package apptive.homework.dto;

import lombok.Getter;

@Getter
public class BoardInfoDto {
    private String title;
    private String content;
    private String name;

    protected BoardInfoDto() {}
    public BoardInfoDto(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
    }
}
