package at.pasztor.backend.post.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogPostCreateRequest {
    public final String slug;
    public final String title;
    public final String content;

    public BlogPostCreateRequest(
            @JsonProperty(required = true)
            String slug,
            @JsonProperty(required = true)
            String title,
            @JsonProperty(required = true)
            String content
    ) {
        this.slug = slug;
        this.title = title;
        this.content = content;
    }
}
