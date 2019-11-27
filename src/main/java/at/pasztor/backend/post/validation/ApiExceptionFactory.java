package at.pasztor.backend.post.validation;

import at.pasztor.backend.post.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zone.refactor.spring.validation.chain.ExceptionFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiExceptionFactory implements ExceptionFactory<ApiException> {
    @Override
    public void create(Map<String, Set<String>> errors) throws ApiException {
        Map<String, Set<ValidationError>> exceptionErrors = new HashMap<>();
        for (Map.Entry<String, Set<String>> error : errors.entrySet()) {
            if (error.getValue().isEmpty()) {
                continue;
            }
            exceptionErrors.put(
                error.getKey(),
                error
                    .getValue()
                    .stream()
                    .map(ValidationError::fromString)
                    .collect(Collectors.toSet())
            );
        }
        if (!exceptionErrors.isEmpty()) {
            throw new ApiException(
                HttpStatus.BAD_REQUEST,
                ApiException.ErrorCode.VALIDATION_FAILED,
                "Please verify your input and correct the following errors.",
                exceptionErrors
            );
        }
    }
}
