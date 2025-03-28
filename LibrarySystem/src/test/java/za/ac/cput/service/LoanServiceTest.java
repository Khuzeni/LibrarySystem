package za.ac.cput.service;

import za.ac.cput.domain.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {
    private LoanService loanService;
    private Loan loan;

    @BeforeEach
    void setUp() {
        loanService = new LoanService();
        loan = new Loan("L001", "B001", "M001", new Date(), new Date(), null);
        loanService.borrowBook(loan);
    }

    @Test
    void testGetLoan() {
        assertEquals(loan, loanService.getLoan("L001"));
    }

    @Test
    void testIsBookLoaned() {
        assertTrue(loanService.isBookLoaned("B001"));
    }

    @Test
    void testReturnBook() {
        Date returnDate = new Date();
        loanService.returnBook("L001", returnDate);
        assertEquals(returnDate, loanService.getLoan("L001").getReturnDate());
    }
}
