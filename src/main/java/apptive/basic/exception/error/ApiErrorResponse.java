package apptive.basic.exception.error;


import lombok.Getter;

import java.util.List;

@Getter
public class ApiErrorResponse {

    private List<FieldErrorResponse> fieldErrors;
    private List<GlobalErrorResponse> globalErrors;

    public ApiErrorResponse(List<FieldErrorResponse> fieldErrors, List<GlobalErrorResponse> globalErrors) {
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }
}
