package za.ac.cput.service;

import za.ac.cput.domain.Librarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibrarianServiceTest {
    private LibrarianService librarianService;
    private Librarian librarian;

    @BeforeEach
    void setUp() {
        librarianService = new LibrarianService();
        librarian = new Librarian("L001", "Alice Smith", "alice@library.com");
        librarianService.addLibrarian(librarian);
    }

    @Test
    void testGetLibrarian() {
        assertEquals(librarian, librarianService.getLibrarian("L001"));
    }
}
