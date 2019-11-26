package at.pasztor.backend.post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity(name = "posts")
@Table(name = "posts")
public class BlogPost {
    @Id
    @Column(nullable = false)
    @ApiModelProperty(
        required = true,
        value = "URL slug",
        notes = "URL part of this blog post.",
        allowableValues = "range(1,255)",
        position = 0
    )
    @Pattern(regexp = "\\A[A-Za-z0-9\\-]+\\Z")
    @JsonProperty(required = true, index = 0)
    public final String slug;

    @ApiModelProperty(
        required = true,
        value = "Title",
        notes = "User-visible title of this post",
        allowableValues = "range(1,255)",
        position = 1
    )
    @JsonProperty(required = true, index = 1)
    @Column(nullable = false)
    public final String title;

    @ApiModelProperty(
        required = true,
        value = "Content",
        notes = "Content of this blog post",
        allowableValues = "range(0,65535)",
        position = 2,
        allowEmptyValue = true
    )
    @JsonProperty(required = true, index = 2)
    @Column(nullable = false, length = 65535)
    public final String content;

    //Only for JPA
    @SuppressWarnings("unused")
    private BlogPost() {
        slug = "";
        title = "";
        content = "";
    }

    public BlogPost(
            String slug,
            String title,
            String content
    ) {
        this.slug = slug;
        this.title = title;
        this.content = content;
    }
}
