package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Book;
import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Member;
import za.ac.cput.repository.BookRepository;
import za.ac.cput.repository.LoanRepository;
import za.ac.cput.repository.MemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashboard{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        try {
            // Total Books
            long totalBooks = bookRepository.count();
            stats.put("totalBooks", totalBooks);

            // Total Members
            long totalMembers = memberRepository.count();
            stats.put("totalMembers", totalMembers);

            // Active Loans (loans without return date)
            long activeLoans = loanRepository.findByReturnDateIsNull().size();
            stats.put("activeLoans", activeLoans);

            // Overdue Books (active loans with due date passed)
            long overdueBooks = loanRepository.findOverdueLoans().size();
            stats.put("overdueBooks", overdueBooks);

        } catch (Exception e) {
            // Fallback to default values if there's an error
            stats.put("totalBooks", 0);
            stats.put("totalMembers", 0);
            stats.put("activeLoans", 0);
            stats.put("overdueBooks", 0);
        }

        return stats;
    }

    @Override
    public Map<String, Object> getRecentLoans() {
        Map<String, Object> result = new HashMap<>();

        try {
            List<Loan> allLoans = loanRepository.findAll();

            // Get recent loans (last 5, sorted by loan date descending)
            List<Map<String, Object>> recentLoans = allLoans.stream()
                    .sorted((l1, l2) -> l2.getLoanDate().compareTo(l1.getLoanDate()))
                    .limit(5)
                    .map(loan -> {
                        Map<String, Object> loanData = new HashMap<>();
                        loanData.put("bookTitle", loan.getBook() != null ? loan.getBook().getTitle() : "Unknown Book");
                        loanData.put("memberName", loan.getMember() != null ? loan.getMember().getName() : "Unknown Member");
                        loanData.put("dueDate", loan.getDueDate().toString());
                        loanData.put("status", getLoanStatus(loan));
                        return loanData;
                    })
                    .collect(Collectors.toList());

            result.put("recentLoans", recentLoans);

        } catch (Exception e) {
            result.put("recentLoans", Collections.emptyList());
        }

        return result;
    }

    @Override
    public Map<String, Object> getRecentlyAddedBooks() {
        Map<String, Object> result = new HashMap<>();

        try {
            List<Book> allBooks = bookRepository.findAll();

            // Get recently added books (last 5)
            List<Map<String, Object>> recentBooks = allBooks.stream()
                    .sorted((b1, b2) -> {
                        // Since we don't have a created date, use ID as proxy (assuming higher IDs are newer)
                        return b2.getBookId().compareTo(b1.getBookId());
                    })
                    .limit(5)
                    .map(book -> {
                        Map<String, Object> bookData = new HashMap<>();
                        bookData.put("title", book.getTitle());
                        bookData.put("author", book.getAuthor());
                        bookData.put("isbn", book.getIsbn());
                        bookData.put("available", book.getIsAvailable());
                        return bookData;
                    })
                    .collect(Collectors.toList());

            result.put("recentBooks", recentBooks);

        } catch (Exception e) {
            result.put("recentBooks", Collections.emptyList());
        }

        return result;
    }

    @Override
    public Map<String, Object> getMostBorrowedBooks() {
        Map<String, Object> result = new HashMap<>();

        try {
            List<Loan> allLoans = loanRepository.findAll();

            // Count loans per book
            Map<Book, Long> bookLoanCount = allLoans.stream()
                    .filter(loan -> loan.getBook() != null)
                    .collect(Collectors.groupingBy(Loan::getBook, Collectors.counting()));

            // Get top 5 most borrowed books
            List<Map<String, Object>> topBooks = bookLoanCount.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(5)
                    .map(entry -> {
                        Map<String, Object> bookData = new HashMap<>();
                        bookData.put("title", entry.getKey().getTitle());
                        bookData.put("author", entry.getKey().getAuthor());
                        bookData.put("loanCount", entry.getValue());
                        return bookData;
                    })
                    .collect(Collectors.toList());

            result.put("mostBorrowedBooks", topBooks);

        } catch (Exception e) {
            result.put("mostBorrowedBooks", Collections.emptyList());
        }

        return result;
    }

    @Override
    public Map<String, Object> getTopMembers() {
        Map<String, Object> result = new HashMap<>();

        try {
            List<Loan> allLoans = loanRepository.findAll();

            // Count loans per member
            Map<Member, Long> memberLoanCount = allLoans.stream()
                    .filter(loan -> loan.getMember() != null)
                    .collect(Collectors.groupingBy(Loan::getMember, Collectors.counting()));

            // Get top 5 members with most loans
            List<Map<String, Object>> topMembers = memberLoanCount.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(5)
                    .map(entry -> {
                        Map<String, Object> memberData = new HashMap<>();
                        memberData.put("name", entry.getKey().getName());
                        memberData.put("email", entry.getKey().getEmail());
                        memberData.put("loanCount", entry.getValue());
                        return memberData;
                    })
                    .collect(Collectors.toList());

            result.put("topMembers", topMembers);

        } catch (Exception e) {
            result.put("topMembers", Collections.emptyList());
        }

        return result;
    }

    @Override
    public Map<String, Object> getMonthlyStatistics() {
        Map<String, Object> result = new HashMap<>();

        try {
            // Sample monthly data - in a real app, you'd query the database
            // by month and year
            Map<String, Object> monthlyData = new HashMap<>();
            monthlyData.put("labels", Arrays.asList("January", "February", "March", "April", "May", "June"));
            monthlyData.put("loans", Arrays.asList(65, 59, 80, 81, 56, 55));
            monthlyData.put("returns", Arrays.asList(45, 48, 65, 72, 45, 50));

            result.put("monthlyStatistics", monthlyData);

        } catch (Exception e) {
            // Fallback data
            Map<String, Object> monthlyData = new HashMap<>();
            monthlyData.put("labels", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"));
            monthlyData.put("loans", Arrays.asList(10, 15, 12, 18, 20, 25));
            monthlyData.put("returns", Arrays.asList(8, 12, 10, 15, 18, 22));

            result.put("monthlyStatistics", monthlyData);
        }

        return result;
    }

    // Helper method to determine loan status
    private String getLoanStatus(Loan loan) {
        LocalDate now = LocalDate.now();
        if (loan.getReturnDate() != null) {
            return "Returned";
        } else if ((LocalDate.now()).isAfter(loan.getDueDate())) {
            return "Overdue";
        } else {
            return "Active";
        }
    }
}
