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

    private Long member_id;

    protected Board() {}

    public Board(String title, String content, String name, Long member_id) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.member_id = member_id;
    }

    public void ChangeTitle(String title) {
        this.title = title;
    }

    public void ChangeContent(String content) {
        this.content = content;
    }
}
