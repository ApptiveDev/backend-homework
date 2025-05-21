package apptive.homework.dto;

import lombok.Getter;

@Getter
public class BoardDto {

    private String title;
    private String content;
    private String name;
    private Long member_id;

    protected BoardDto() {}

    public BoardDto(String title, String content, String name, Long member_id) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.member_id = member_id;
    }
}
