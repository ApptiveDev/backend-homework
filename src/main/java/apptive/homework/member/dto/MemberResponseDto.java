package apptive.homework.member.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;

    public MemberResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
