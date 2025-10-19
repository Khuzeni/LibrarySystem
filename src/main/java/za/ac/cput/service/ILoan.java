package za.ac.cput.service;

import za.ac.cput.domain.Loan;

import java.util.List;

public interface ILoan  {
    Loan create(Loan loan);
    Loan read(Long loanId);
    Loan update(Loan loan);
    boolean delete(Long loanId); // Change return type to boolean
    List<Loan> getAllLoans();
    Loan returnBook(Long loanId); // Add this method
}