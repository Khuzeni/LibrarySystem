package za.ac.cput.service;

import za.ac.cput.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
    private BookService bookService;
    private Book book;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        book = new Book("B001", "Clean Code", "Robert C. Martin", "978-0132350884", true);
        bookService.addBook(book);
    }

    @Test
    void testGetBook() {
        assertEquals(book, bookService.getBook("B001"));
        System.out.println("We still have the qhoo !!!");
    }

    @Test
    void testIsBookAvailable() {
        assertTrue(bookService.isBookAvailable("B001"));
        System.out.println("Test2 Successful !!!");
    }

    @Test
    void testUpdateAvailability() {
        bookService.updateAvailability("B001", false);
        assertFalse(bookService.isBookAvailable("B001"));
        System.out.println("Test3 Successful !!!");
    }
}
