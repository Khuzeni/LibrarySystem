package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Remove this method - use findById instead
    // Loan findByLoanId(Long loanId);

    List<Loan> findByReturnDateIsNull();

    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.dueDate < CURRENT_DATE")
    List<Loan> findOverdueLoans();

    List<Loan> findByMember_MemberId(Long memberId);

    List<Loan> findByBook_BookId(Long bookId);
}