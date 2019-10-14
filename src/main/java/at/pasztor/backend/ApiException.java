package at.pasztor.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(value = {
        "detailMessage",
        "cause",
        "stackTrace",
        "suppressedExceptions",
        "backtrace",
        "message",
        "localizedMessage",
        "suppressed"
})
public class ApiException extends Exception {
    @JsonIgnore
    public final HttpStatus status;
    public final ErrorCode error;
    public final String errorMessage;

    public ApiException(HttpStatus status, ErrorCode error, String errorMessage) {
        this.status = status;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public enum ErrorCode {
        TEST,
        NOT_FOUND
    }
}
