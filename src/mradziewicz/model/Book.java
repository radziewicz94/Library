package mradziewicz.model;

public class Book extends Publication {
    private int pages;
    private int releaseDate;
    private String isbn;
    public static final String BOOK_TYPE = "Książka";

    public Book(String title, String author, int pages, String publisher, int releaseDate, String isbn) {
        super(title, author, publisher);
        this.pages = pages;
        this.releaseDate = releaseDate;
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toCsv() {
        return BOOK_TYPE + ";" + getTitle() + ";" + getAuthor() + ";" + pages + ";" + getPublisher() + ";"
                + releaseDate + ";" + isbn;
    }

    @Override
    public String toString() {
        return super.toString() + "; " + pages +
                "; " + releaseDate +
                "; " + isbn;
    }
}
