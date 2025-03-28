package za.ac.cput.service;

import za.ac.cput.domain.Book;
import java.util.HashMap;
import java.util.Map;

public class BookService {
    private Map<String, Book> bookCollection = new HashMap<>();

        public void addBook(Book book) {
        bookCollection.put(book.getBookId(), book);
    }

        public Book getBook(String bookId) {
        return bookCollection.get(bookId);
    }

    public boolean isBookAvailable(String bookId) {
        Book book = bookCollection.get(bookId);
        return book != null && book.isAvailable();
    }

    public void updateAvailability(String bookId, boolean isAvailable) {
            if (bookCollection.containsKey(bookId)) {
            bookCollection.get(bookId).setAvailable(isAvailable);
        }
    }
}
