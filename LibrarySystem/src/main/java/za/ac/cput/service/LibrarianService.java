package za.ac.cput.service;

import za.ac.cput.domain.Librarian;
import java.util.HashMap;
import java.util.Map;

public class LibrarianService {
    private Map<String, Librarian> librarians = new HashMap<>();

        public void addLibrarian(Librarian librarian) {
        librarians.put(librarian.getLibrarianId(), librarian);
    }

        public Librarian getLibrarian(String librarianId) {
        return librarians.get(librarianId);
    }
}
