package at.pasztor.backend.post.validation;

import at.pasztor.backend.post.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zone.refactor.spring.validation.annotation.ValidatorProvider;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public class RefactorZoneEntityValidator<T> implements EntityValidator<T> {
    private final zone.refactor.spring.validation.entity.EntityValidator<T, ApiException> backendValidator;
    private final ApiExceptionFactory apiExceptionFactory;

    @Autowired
    public RefactorZoneEntityValidator(
        Collection<ValidatorProvider> validatorProviders,
        ApiExceptionFactory apiExceptionFactory
    ) {
        this.backendValidator = new zone.refactor.spring.validation.entity.EntityValidator<>(
            apiExceptionFactory,
            validatorProviders
        );
        this.apiExceptionFactory = apiExceptionFactory;
    }

    @Override
    public void validate(T entity) throws ApiException {
        final Map<String, Set<String>> errors = backendValidator.validate(entity);
        apiExceptionFactory.create(errors);
    }
}
