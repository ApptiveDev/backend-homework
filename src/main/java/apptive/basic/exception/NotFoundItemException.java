package apptive.basic.exception;

public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(String message) {
        super(message);
    }
}
