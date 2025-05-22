package apptive.homework.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @NotBlank @Size(min = 10, max = 20)
    private String title;

    @NotBlank @Size(min = 3)
    private String content;

    @NotBlank @Size(min = 3)
    private String name;

    private Long memberId;
}