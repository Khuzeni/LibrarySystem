package za.ac.cput.factory;

import za.ac.cput.domain.Librarian;

public class LibrarianFactory {
        public static Librarian createLibrarian(String librarianId, String name, String email) {
        return new Librarian(librarianId, name, email);
    }
}
