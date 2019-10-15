package at.pasztor.backend.post.api;

import org.springframework.lang.Nullable;

public class BlogPostUpdateRequest {
    @Nullable
    public final String title;
    @Nullable
    public final String content;

    public BlogPostUpdateRequest(
            @Nullable
            String title,
            @Nullable
            String content
    ) {
        this.title = title;
        this.content = content;
    }
}
