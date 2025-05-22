package apptive.homework.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("권한이 없습니다.");
    }
}
