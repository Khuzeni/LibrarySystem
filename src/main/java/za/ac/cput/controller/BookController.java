package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Book;
import za.ac.cput.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        System.out.println("GET /api/books called");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        System.out.println("GET /api/books/" + id + " called");
        Book book = bookService.read(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        System.out.println("POST /api/books called with: " + book);
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        System.out.println("PUT /api/books/" + id + " called");
        Book existingBook = bookService.read(id);
        if (existingBook != null) {
            Book updatedBook = new Book.Builder()
                    .copy(existingBook)
                    .setTitle(bookDetails.getTitle())
                    .setAuthor(bookDetails.getAuthor())
                    .setIsbn(bookDetails.getIsbn())
                    .setIsAvailable(bookDetails.getIsAvailable())
                    .build();
            return ResponseEntity.ok(bookService.update(updatedBook));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        System.out.println("DELETE /api/books/" + id + " called");
        boolean deleted = bookService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        System.out.println("GET /api/books/available called");
        return bookService.findByStatus(true);
    }
}
