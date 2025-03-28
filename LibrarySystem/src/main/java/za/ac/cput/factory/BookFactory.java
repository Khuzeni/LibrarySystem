package za.ac.cput.factory;

import za.ac.cput.domain.Book;

public class BookFactory {
        public static Book createBook(String bookId, String title, String author, String isbn, boolean isAvailable) {
        return new Book(bookId, title, author, isbn, isAvailable);
    }
}
