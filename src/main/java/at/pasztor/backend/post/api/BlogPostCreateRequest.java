package at.pasztor.backend.post.api;

import at.pasztor.backend.post.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BlogPostCreateRequest {
    @ApiModelProperty(
        required = true,
        value = "URL slug",
        notes = "URL part of this blog post.",
        allowableValues = "range(1,255)",
        position = 0
    )
    @Pattern(regexp = "\\A[a-zA-Z0-9\\-]+\\Z")
    @JsonProperty(required = true, index = 0)
    public final String slug;

    @ApiModelProperty(
        required = true,
        value = "Title",
        notes = "User-visible title of this post",
        allowableValues = "range(1,255)",
        position = 1
    )
    @NotNull(message = "The title is required.")
    @JsonProperty(required = true, index = 1)
    public final String title;

    @ApiModelProperty(
        required = true,
        value = "Content",
        notes = "Content of this blog post",
        allowableValues = "range(0,65535)",
        position = 2,
        allowEmptyValue = true
    )
    @NotNull(message = "The title is required.")
    @JsonProperty(required = true, index = 2)
    public final String content;

    @JsonCreator
    public BlogPostCreateRequest(
        @JsonProperty(value = "slug")
        String slug,

        @JsonProperty(value = "title")
        String title,

        @JsonProperty(value = "content")
        String content
    ) throws ApiException {
        this.slug = slug;
        this.title = title;
        this.content = content;
    }
}
