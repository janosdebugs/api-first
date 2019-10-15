package at.pasztor.backend.post.storage;

import at.pasztor.backend.ApiException;
import at.pasztor.backend.post.entity.BlogPost;
import java.util.List;

public interface BlogPostStorage {
    void store(BlogPost post);
    BlogPost getBySlug(String slug) throws ApiException;
    void delete(BlogPost post);
    List<BlogPost> list();
}
