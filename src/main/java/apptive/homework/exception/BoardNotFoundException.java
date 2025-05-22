package apptive.homework.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException() {
        super("존재하지 않는 보드입니다.");
    }
}
