package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Librarian;
import za.ac.cput.service.LibrarianService;

import java.util.List;

@RestController
@RequestMapping("/api/librarians")
@CrossOrigin(origins = "http://localhost:3000")

public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable Long id) {
        Librarian librarian = librarianService.read(id);
        return librarian != null ? ResponseEntity.ok(librarian) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Librarian> createLibrarian(@RequestBody Librarian librarian) {
        try {
            Librarian created = librarianService.create(librarian);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Librarian> updateLibrarian(@PathVariable Long id, @RequestBody Librarian librarian) {
        try {
            // Ensure the ID in the path matches the librarian ID
            librarian = new Librarian.Builder().copy(librarian).setLibrarianId(id).build();
            Librarian updated = librarianService.update(librarian);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrarian(@PathVariable Long id) {
        boolean deleted = librarianService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
