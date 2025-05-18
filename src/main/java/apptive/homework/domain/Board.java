package apptive.homework.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long board_id;

    private String title;
    private String content;
    private  String name;
}
