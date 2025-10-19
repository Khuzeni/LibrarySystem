package za.ac.cput.service;

import za.ac.cput.domain.Book;

import java.util.List;
import java.util.Optional;

public interface IBook {
    Book create(Book book);
    Book read(Long bookId);
    Book update(Book book);
    boolean delete(Long bookId);
    List<Book> getAllBooks();

    Optional<Book> getBookByBookId(String bookId);
    List<Book> searchBooks(String query);

}


//        extends IService<Book, Long> {
//
//    List<Book> getAllBooks();
//
//    Optional<Book> getBookByBookId(String bookId);
//}
