package at.pasztor.backend.post.validation;

import at.pasztor.backend.post.exception.ApiException;

public interface EntityValidator<T> {
    void validate(T entity) throws ApiException;
}
