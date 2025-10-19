package za.ac.cput.factory;

import za.ac.cput.domain.Book;

public class BookFactory {
    public static Book createBook(Long bookId, String title, String author, boolean available, String isbn) {
        return new Book.Builder()
                .setBookId(bookId)
                .setTitle(title)
                .setAuthor(author)
                .setIsbn(isbn)
                .build();
    }
}
