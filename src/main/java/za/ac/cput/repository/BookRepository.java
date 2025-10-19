package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Option 1: Remove this method if you don't need it
    // List<Book> findByAvailable(boolean available);

    // Option 2: Use a custom query if you have a different field
    @Query("SELECT b FROM Book b WHERE b.IsAvailable = :status")
    List<Book> findByStatus(boolean status);

    //Option 3: Custom query for searching books
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:query% OR b.author LIKE %:query% OR b.isbn LIKE %:query%")
    List<Book> searchBooks(String query);
}


