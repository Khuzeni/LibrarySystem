package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Librarian;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianFactoryTest {

    @Test
    void createLibrarian() {
        Librarian librarian = LibrarianFactory.createLibrarian(
                3434L,
                "John",
                "John@gmail.com"
        );

        assertNotNull(librarian);
        System.out.println(librarian);
    }
}