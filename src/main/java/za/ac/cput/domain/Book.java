package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long bookId;
    private String title;
    private String author;
    private boolean IsAvailable;
    private String isbn;

    // Default constructor
    public Book() {}


    private Book(Builder builder) {
        this.bookId = builder.bookId;
        this.title = builder.title;
        this.author = builder.author;
        this.IsAvailable = builder.IsAvailable;
        this.isbn = builder.isbn;
    }

    //GETTERS
    public Long getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean getIsAvailable() {
        return IsAvailable;
    }
    public String getIsbn() {
        return isbn;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", IsAvailable=" + IsAvailable +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    //BUILDER PATTERN
    public static class Builder {
        private Long bookId;
        private String title;
        private String author;
        private boolean IsAvailable;
        private String isbn;

        //SETTERS FOR BUILDER PATTERN
        public Builder setBookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setIsAvailable(boolean isAvailable) {
            this.IsAvailable = isAvailable;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder copy(Book book) {
            this.bookId = book.bookId;
            this.title = book.title;
            this.author = book.author;
            this.IsAvailable = book.IsAvailable;
            this.isbn = book.isbn;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}



