package at.pasztor.backend.post.exception;

import at.pasztor.backend.post.validation.ValidationError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @ApiModelProperty(required = true, position = 0, value = "Error code")
    @JsonProperty(required = true, index = 0)
    public final ErrorCode error;

    @ApiModelProperty(required = true, position = 1, value = "Error message")
    @JsonProperty(required = true, index = 1)
    public final String errorMessage;

    @ApiModelProperty(required = true, position = 2, value = "Validation errors", notes = "Validation errors, keyed with the field")
    @JsonProperty(required = true, index = 2)
    public final Map<String, Set<ValidationError>> validationErrors;

    public ApiException(HttpStatus status, ErrorCode error, String errorMessage, Map<String, Set<ValidationError>> validationErrors) {
        this.status = status;
        this.error = error;
        this.errorMessage = errorMessage;
        this.validationErrors = validationErrors;
    }

    public enum ErrorCode {
        VALIDATION_FAILED,
        NOT_FOUND;
    }
}
