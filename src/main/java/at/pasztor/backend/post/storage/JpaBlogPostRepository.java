package at.pasztor.backend.post.storage;

import at.pasztor.backend.post.entity.BlogPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

public interface JpaBlogPostRepository extends CrudRepository<BlogPost, String> {
    @Query("SELECT p FROM posts p WHERE p.title LIKE CONCAT('%', CONCAT(?1, '%'))")
    Iterator<BlogPost> getByTitle(String title);
}
