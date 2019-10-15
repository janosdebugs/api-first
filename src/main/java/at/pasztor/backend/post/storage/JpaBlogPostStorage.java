package at.pasztor.backend.post.storage;

import at.pasztor.backend.ApiException;
import at.pasztor.backend.post.entity.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaBlogPostStorage implements BlogPostStorage {
    private final JpaBlogPostRepository repository;

    @Autowired
    public JpaBlogPostStorage(JpaBlogPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void store(BlogPost post) {
        repository.save(post);
    }

    @Override
    public BlogPost getBySlug(String slug) throws ApiException {
        Optional<BlogPost> post = repository.findById(slug);
        if (!post.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, ApiException.ErrorCode.NOT_FOUND, "The blog post you requested was not found.");
        }
        return post.get();
    }

    @Override
    public void delete(BlogPost post) {
        repository.delete(post);
    }

    @Override
    public List<BlogPost> list() {
        Iterable<BlogPost> posts = repository.findAll();
        ArrayList<BlogPost> postList = new ArrayList<>();
        posts.forEach(postList::add);
        return postList;
    }
}
