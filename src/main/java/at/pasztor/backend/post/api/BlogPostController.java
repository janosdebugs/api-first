package at.pasztor.backend.post.api;

import at.pasztor.backend.post.exception.ApiException;
import at.pasztor.backend.post.entity.BlogPost;
import at.pasztor.backend.post.storage.BlogPostStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(
    tags = "Blog posts"
)
@RestController
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostStorage storage;

    @Autowired
    public BlogPostController(BlogPostStorage storage) {
        this.storage = storage;
    }

    @ApiOperation(
        nickname = "create",
        value = "Create a post",
        notes = "Creates a blog post by providing its details."
    )
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BlogPost create(
        @NotNull
        @ApiParam(required = true)
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

    @ApiOperation(
        nickname = "get",
        value = "Get a post",
        notes = "Get a blog post by it's URL slug"
    )
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

    @ApiOperation(
        nickname = "list",
        value = "List posts",
        notes = "List all blog posts."
    )
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<BlogPost> list() {
        return storage.list();
    }

    @ApiOperation(
        nickname = "update",
        value = "Update a post",
        notes = "Update a post by providing the fields that should be updated."
    )
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

    @ApiOperation(
        nickname = "delete",
        value = "Delete a post",
        notes = "Delete a post by providing it's URL slug."
    )
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
