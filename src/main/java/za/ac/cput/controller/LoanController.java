package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Loan;
import za.ac.cput.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        System.out.println("GET /api/loans called");
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        System.out.println("GET /api/loans/" + id + " called");
        Loan loan = loanService.read(id);
        return loan != null ? ResponseEntity.ok(loan) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        System.out.println("POST /api/loans called with: " + loan);
        try {
            Loan createdLoan = loanService.create(loan);
            return ResponseEntity.ok(createdLoan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {
        System.out.println("PUT /api/loans/" + id + "/return called");
        try {
            Loan returnedLoan = loanService.returnBook(id);
            return ResponseEntity.ok(returnedLoan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
        System.out.println("DELETE /api/loans/" + id + " called");
        boolean deleted = loanService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}