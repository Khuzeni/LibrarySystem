package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Librarian;
import za.ac.cput.domain.Loan;
import za.ac.cput.repository.LibrarianRepository;

import java.util.List;

@Service
public class LibrarianService implements ILibrarian {
    private final LibrarianRepository librarianRepository;

    @Autowired
    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Librarian create(Librarian librarian) {
        if (librarian.getLibrarianId() != null && librarianRepository.existsById(librarian.getLibrarianId())) {
            throw new IllegalArgumentException("Librarian Id already exists: " + librarian.getLibrarianId());
        }
        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian read(Long librarianId) {
        return librarianRepository.findById(librarianId).orElse(null);
    }

    @Override
    public Librarian update(Librarian librarian) {
        if (librarian.getLibrarianId() == null || !librarianRepository.existsById(librarian.getLibrarianId())) {
            throw new IllegalArgumentException("Librarian not found with ID: " + librarian.getLibrarianId());
        }
        return librarianRepository.save(librarian);
    }
    @Override
    public boolean delete(Long librarianId) {
        if (librarianRepository.existsById(librarianId)) {
            librarianRepository.deleteById(librarianId);
            return true;
        }
        return false;
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    // Additional methods
    public Librarian findByEmail(String email) {
        return librarianRepository.findByEmail(email);
    }
}