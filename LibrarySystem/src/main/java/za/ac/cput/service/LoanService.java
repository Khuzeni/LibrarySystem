package za.ac.cput.service;

import za.ac.cput.domain.Loan;
import java.util.*;

public class LoanService {
    private Map<String, Loan> activeLoans = new HashMap<>();

    public void borrowBook(Loan loan) {
        activeLoans.put(loan.getLoanId(), loan);
    }

    public Loan getLoan(String loanId) {
        return activeLoans.get(loanId);
    }

    public void returnBook(String loanId, Date returnDate) {
        if (activeLoans.containsKey(loanId)) {
            activeLoans.get(loanId).setReturnDate(returnDate);
        }
    }

    public boolean isBookLoaned(String bookId) {
        return activeLoans.values().stream().anyMatch(loan -> loan.getBookId().equals(bookId) && loan.getReturnDate() == null);
    }
}
