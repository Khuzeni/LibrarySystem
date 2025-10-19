package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Loan;
import za.ac.cput.repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService implements ILoan {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan create(Loan loan) {
        if (loan.getLoanId() != null && loanRepository.existsById(loan.getLoanId())) {
            throw new IllegalArgumentException("Loan ID already exists: " + loan.getLoanId());
        }
        return loanRepository.save(loan);
    }

    @Override
    public Loan read(Long loanId) {
        return loanRepository.findById(loanId).orElse(null);
    }

    @Override
    public Loan update(Loan loan) {
        if (loan.getLoanId() == null || !loanRepository.existsById(loan.getLoanId())) {
            throw new IllegalArgumentException("Loan not found with ID: " + loan.getLoanId());
        }
        return loanRepository.save(loan);
    }

    @Override
    public boolean delete(Long loanId) {
        if (loanRepository.existsById(loanId)) {
            loanRepository.deleteById(loanId);
            return true;
        }
        return false;
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null) {
            throw new IllegalArgumentException("Loan not found with ID: " + loanId);
        }

        // Option 1: If you have setters in your Loan class
        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);

        // Option 2: If you don't want setters, use Builder pattern
        // Loan returnedLoan = new Loan.Builder()
        //         .copy(loan)
        //         .setReturnDate(LocalDate.now())
        //         .build();
        // return loanRepository.save(returnedLoan);
    }

    // Additional business methods
    public List<Loan> findActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    public List<Loan> findOverdueLoans() {
        return loanRepository.findOverdueLoans();
    }

    public List<Loan> findByMemberId(Long memberId) {
        return loanRepository.findByMember_MemberId(memberId);
    }
}