package at.pasztor.backend.post.api;

import at.pasztor.backend.ApiException;
import at.pasztor.backend.post.entity.BlogPost;
import at.pasztor.backend.post.storage.BlogPostStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostStorage storage;

    @Autowired
    public BlogPostController(BlogPostStorage storage) {
        this.storage = storage;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BlogPost create(
            @RequestBody
            BlogPostCreateRequest request
    ) {
        BlogPost post = new BlogPost(
                request.slug,
                request.title,
                request.content
        );
        storage.store(post);
        return post;
    }

    @RequestMapping(
            value = "/{slug}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BlogPost get(
            @PathVariable
            String slug
    ) throws ApiException {
        return storage.getBySlug(slug);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<BlogPost> list() {
        return storage.list();
    }

    @RequestMapping(
            value = "/{slug}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BlogPost update(
            @PathVariable
            String slug,
            @RequestBody
            BlogPostUpdateRequest request
    ) throws ApiException {
        BlogPost post = storage.getBySlug(slug);

        String title = request.title==null?post.title:request.title;
        String content = request.content==null?post.content:request.content;

        post = new BlogPost(
                post.slug,
                title,
                content
        );
        storage.store(post);
        return post;
    }

    @RequestMapping(
            value = "/{slug}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void delete(
            @PathVariable
            String slug
    ) throws ApiException {
        storage.delete(storage.getBySlug(slug));
    }
}
