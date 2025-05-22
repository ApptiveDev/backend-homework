package apptive.basic.exception.error;

import lombok.Getter;

@Getter
public class FieldErrorResponse {
    String field;
    String message;

    public FieldErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
