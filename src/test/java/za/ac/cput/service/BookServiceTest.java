package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Book;
import za.ac.cput.factory.BookFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceTest {

    @Autowired
    private BookService service; // Use the implementation, not the interface

    private static Book book = BookFactory.createBook(
            800000000L,
            "Get Rich Quick",
            "Don Khuzeni",
            true,
            "9780743273565"
    );

    @Test
    @Order(1)
    void create() {
        Book created = service.create(book);
        assertNotNull(created);
        assertEquals(book.getBookId(), created.getBookId());
        System.out.println("Created Book: " + created);
    }

    @Test
    @Order(2)
    void read() {
        Book read = service.read(book.getBookId());
        assertNotNull(read);
        assertEquals(book.getBookId(), read.getBookId());
        System.out.println("Read Book: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Book updatedBook = new Book.Builder()
                .copy(book)
                .setTitle("The Great Gatsby - Updated Edition")
                .build();
        Book updated = service.update(updatedBook);
        assertNotNull(updated);
        assertEquals("The Great Gatsby - Updated Edition", updated.getTitle());
        System.out.println("Updated Book: " + updated);
    }

    @Test
    @Order(5)
    void getAllBooks() {
        java.util.List<Book> books = service.getAllBooks();
        assertNotNull(books);
        System.out.println("All Books: " + books);
    }

    @Test
    @Order(6)
    void delete() {
        boolean deleted = service.delete(book.getBookId());
        assertTrue(deleted);
        System.out.println("Deleted Book with ID: " + book.getBookId());
    }
}