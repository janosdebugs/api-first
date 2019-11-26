package at.pasztor.backend.post.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

public class BlogPostUpdateRequest {
    @Nullable
    @ApiModelProperty(
        required = false,
        value = "Title",
        notes = "User-visible title of this post",
        allowableValues = "range(1,255)",
        position = 1
    )
    @JsonProperty(required = false, index = 1)
    public final String title;

    @Nullable
    @ApiModelProperty(
        required = false,
        value = "Content",
        notes = "Content of this blog post",
        allowableValues = "range(0,65535)",
        position = 2,
        allowEmptyValue = true
    )
    @JsonProperty(required = false, index = 2)
    public final String content;

    @JsonCreator
    public BlogPostUpdateRequest(
            @Nullable
            @JsonProperty(required = false, index = 1)
            String title,
            @Nullable
            @JsonProperty(required = false, index = 2)
            String content
    ) {
        this.title = title;
        this.content = content;
    }
}
