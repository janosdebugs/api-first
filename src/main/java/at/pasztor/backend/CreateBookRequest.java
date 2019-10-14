package at.pasztor.backend;

public class CreateBookRequest {
    public final String title;
    public final String author;

    public CreateBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
