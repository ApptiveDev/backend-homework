package apptive.homework.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "members",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Email @NotBlank
    private String email;

    @NotBlank @Size(min = 3)
    private String name;

    @NotBlank @Size(min = 10)
    private String password;
}
