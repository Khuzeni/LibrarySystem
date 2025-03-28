package za.ac.cput.domain;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String bookId, String title, String author, String isbn, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { this.isAvailable = available; }

    @Override
    public String toString() {
        return "Book{" + "bookId='" + bookId + '\'' + ", title='" + title + '\'' +
                ", author='" + author + '\'' + ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable + '}';
    }
}
