package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Librarian;
import za.ac.cput.factory.LibrarianFactory;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianServiceTest {
    private ILibrarian service;

    private Librarian librarian = LibrarianFactory.createLibrarian(
            900000000L,
            "Alice",
            "Johnson"
            );

    @Test
    void create() {
        Librarian created = service.create(librarian);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        Librarian read = service.read(librarian.getLibrarianId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        Librarian updatedLibrarian = new Librarian.Builder()
                .copy(librarian)
                .setFirstName("Smith")
                .build();
        Librarian updated = service.update(updatedLibrarian);
        assertNotNull(updated);
        assertEquals(updatedLibrarian.getFirstName(), updated.getFirstName());
        System.out.println("Updated: " + updated);
    }

    @Test
    void delete() {
        boolean deleted = service.delete(librarian.getLibrarianId());
        assertNotNull(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    void getAllLibrarians() {
        System.out.println("All Librarians: ");
        System.out.println(service.getAllLibrarians());
    }
}