package mradziewicz.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable {
    private String title;
    private String author;
    private String publisher;

    public Publication(String title, String author, String publisher) {
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

    public abstract String toCsv();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication publication1 = (Publication) o;
        return Objects.equals(title, publication1.title) &&
                Objects.equals(author, publication1.author) &&
                Objects.equals(publisher, publication1.publisher);
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
