package za.ac.cput.factory;

import za.ac.cput.domain.Book;
import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Member;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class LoanFactory {

    public static Loan createLoan(Book book, Member member, int weeks) {
        if (book == null || member == null) {
            throw new IllegalArgumentException("Book and member are required");
        }

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusWeeks(weeks);

        return new Loan.Builder()
                .setBook(book)
                .setMember(member)
                .setLoanDate(loanDate)
                .setDueDate(dueDate)
                .setReturnDate(null) // Active loan
                .build();
    }

    public static Loan createLoanWithDates(Book book, Member member,
                                           LocalDate loanDate, LocalDate dueDate) {
        if (book == null || member == null || loanDate == null || dueDate == null) {
            throw new IllegalArgumentException("All required fields must be provided");
        }

        if (dueDate.isBefore(loanDate)) {
            throw new IllegalArgumentException("Due date cannot be before loan date");
        }

        return new Loan.Builder()
                .setBook(book)
                .setMember(member)
                .setLoanDate(loanDate)
                .setDueDate(dueDate)
                .setReturnDate(null)
                .build();
    }

    public static Loan createReturnedLoan(Book book, Member member,
                                          LocalDate loanDate, LocalDate dueDate,
                                          LocalDate returnDate) {
        if (book == null || member == null || loanDate == null ||
                dueDate == null || returnDate == null) {
            throw new IllegalArgumentException("All required fields must be provided");
        }

        if (returnDate.isBefore(loanDate)) {
            throw new IllegalArgumentException("Return date cannot be before loan date");
        }

        return new Loan.Builder()
                .setBook(book)
                .setMember(member)
                .setLoanDate(loanDate)
                .setDueDate(dueDate)
                .setReturnDate(returnDate)
                .build();
    }
}