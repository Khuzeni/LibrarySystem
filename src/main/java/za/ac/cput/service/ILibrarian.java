package za.ac.cput.service;


import za.ac.cput.domain.Librarian;

import java.util.List;

public interface ILibrarian {
    Librarian create(Librarian librarian);
    Librarian read(Long librarianId);
    Librarian update(Librarian librarian);
    boolean delete(Long librarianId); // Fixed return type
    List<Librarian> getAllLibrarians();
}

