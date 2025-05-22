package apptive.homework.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;

    protected Member() {}

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
