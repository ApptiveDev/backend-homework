package apptive.homework.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String name;
    private Long memberId;

    protected Board() {}

    public Board(String title, String content, String name, Long memberId) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.memberId = memberId;
    }

    public void changeTitle(String newTitle) {this.title = newTitle;}
    public void changeContent(String newContent) {this.content = newContent;}
    public void changeName(String newName) {this.name = newName;}
}
