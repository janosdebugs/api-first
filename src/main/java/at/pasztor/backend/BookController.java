package at.pasztor.backend;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @RequestMapping(
            value = "/books",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String create(
            @RequestParam
            String title,
            @RequestParam
            String author
    ) {
        return "Created a book called " + title + " by " + author;
    }

    @RequestMapping(
            value = "/books",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String create(
            @RequestBody
            CreateBookRequest request
    ) {
        return "Created a book called " + request.title + " by " + request.author;
    }

    @RequestMapping(
            value = "/books"
    )
    public String search(
            @Nullable
            @RequestParam(required = false)
            String search
    ) {
        if (search == null) {
            return "Not searching for anything";
        } else {
            return "Searching for " + search;
        }
    }

    @RequestMapping(
            value = "/books/{id}"
    )
    public String book(
            @PathVariable
            String id
    ) {
        return "This is book " + id;
    }
}
