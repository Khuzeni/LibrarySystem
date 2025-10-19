package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Book;
import za.ac.cput.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBook{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book read(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
}

    @Override
    public Book update(Book book) {
    if (bookRepository.existsById(book.getBookId())) {
        return bookRepository.save(book);
    }
    return null;
}

    @Override
    public boolean delete(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
    }
    return false;
}
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookByBookId(String bookId) {
        return Optional.empty();
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }

    public List<Book> findByStatus(boolean status) {
        return bookRepository.findByStatus(status);
    }
}
