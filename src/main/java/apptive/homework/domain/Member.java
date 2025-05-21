package apptive.homework.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long member_id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String name;

    protected Member() {};

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return this.member_id;
    }
}
