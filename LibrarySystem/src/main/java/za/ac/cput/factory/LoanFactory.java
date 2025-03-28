package za.ac.cput.factory;

import za.ac.cput.domain.Loan;
import java.util.Date;

public class LoanFactory {
        public static Loan createLoan(String loanId, String bookId, String memberId, Date loanDate, Date dueDate, Date returnDate) {
        return new Loan(loanId, bookId, memberId, loanDate, dueDate, returnDate);
    }
}
