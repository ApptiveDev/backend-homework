package apptive.basic.exception;

public class NotFoundToDoException extends RuntimeException {
    public NotFoundToDoException(String message) {
        super(message);
    }
}
