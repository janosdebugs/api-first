package at.pasztor.backend.post.validation;

import com.fasterxml.jackson.annotation.JsonValue;

import java.security.InvalidParameterException;

public enum ValidationError {
    EXACT_VALUE("exact-value"),
    DOMAIN_NAME_FORMAL("domain-name-formal"),
    EMAIL("invalid-email"),
    HTTP_URL("http-url"),
    IN_LIST("in-list"),
    INTEGER("integer"),
    MAXIMUM_LENGTH("maximum-length"),
    MAXIMUM("maximum"),
    MINIMUM_LENGTH("minimum-length"),
    MINIMUM("minimum"),
    PATTERN("pattern"),
    REQUIRED("required"),
    SINGLE_LINE("single-line"),
    UUID("uuid");

    private final String key;

    ValidationError(String key) {
        this.key = key;
    }

    public static ValidationError fromString(String value) {
        if (value == null) {
            return null;
        }
        for (ValidationError v : ValidationError.values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new InvalidParameterException("Invalid value for ValidationErrror: " + value);
    }

    @JsonValue
    public String toString() {
        return this.key;
    }
}
