package at.pasztor.backend.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "posts")
@Table(name = "posts")
public class BlogPost {
    @Id
    @Column(nullable = false)
    public final String slug;
    @Column(nullable = false)
    public final String title;
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
