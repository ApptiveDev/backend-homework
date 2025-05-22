package apptive.basic.exception.error;

import lombok.Getter;

@Getter
public class GlobalErrorResponse {

    private String message;

    public GlobalErrorResponse(String message) {
        this.message = message;
    }
}
