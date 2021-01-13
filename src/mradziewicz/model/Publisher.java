package mradziewicz.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publisher implements Serializable {
    private String title;
    private String author;
    private String publisher;

    public Publisher(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher1 = (Publisher) o;
        return Objects.equals(title, publisher1.title) &&
                Objects.equals(author, publisher1.author) &&
                Objects.equals(publisher, publisher1.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publisher);
    }

    @Override
    public String toString() {
        return title + "; " + author + "; " + publisher;
    }
}
