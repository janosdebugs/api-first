package at.pasztor.backend.post.validation;

import com.fasterxml.jackson.annotation.JsonValue;

import java.security.InvalidParameterException;

public enum ValidationError {
    EXACT_VALUE("exact-value", "please-enter-x-here"),
    DOMAIN_NAME_FORMAL("domain-name-formal", "please-enter-valid-domain"),
    EMAIL("invalid-email", "please-enter-valid-email"),
    HTTP_URL("http-url", "please-enter-valid-http-url"),
    IN_LIST("in-list", "please-enter-one-of"),
    INTEGER("integer", "please-enter-a-number"),
    MAXIMUM_LENGTH("maximum-length", "please-enter-at-most-x-characters"),
    MAXIMUM("maximum", "please-enter-number-lower-or-equal"),
    MINIMUM_LENGTH("minimum-length", "please-enter-at-least-x-characters"),
    MINIMUM("minimum", "please-enter-number-higher-or-equal"),
    PATTERN("pattern", "please-enter-according-to-pattern"),
    REQUIRED("required", "please-fill-in-this-field"),
    SINGLE_LINE("single-line", "please-enter-single-line"),
    UUID("uuid", "please-enter-a-valid-uuid");

    private final String key;
    private final String localizationKey;

    ValidationError(String key, String localizationKey) {
        this.key = key;
        this.localizationKey = localizationKey;
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

    public String getLocalizationKey() {
        return this.localizationKey;
    }
}
