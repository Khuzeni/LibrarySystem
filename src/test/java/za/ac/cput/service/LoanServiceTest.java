package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Book;
import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Member;
import za.ac.cput.factory.BookFactory;
import za.ac.cput.factory.LoanFactory;
import za.ac.cput.factory.MemberFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    private static Book testBook;
    private static Member testMember;
    private static Loan testLoan;

    @BeforeAll
    static void setUp() {
        // Create test objects first
        testBook = BookFactory.createBook(
                8767678767878L,
                "THE Author",
                "Some Author",
                true,
                "9781234567890"
        );

        testMember = MemberFactory.createMember(
                456543456543456L,
                "Don Khuzeni",
                "steezy.zikalala@gmail.com",
                "0725101873"
        );
    }

    @Test
    @Order(1)
    void prepareTestData() {
        // Save book and member to get their generated IDs
        Book savedBook = bookService.create(testBook);
        Member savedMember = memberService.create(testMember);

        testBook = savedBook;
        testMember = savedMember;

        // Create loan using the factory
        testLoan = LoanFactory.createLoan(
                testBook,
                testMember,
                2 // 2 weeks loan period
        );

        assertNotNull(testBook.getBookId());
        assertNotNull(testMember.getMemberId());
        System.out.println("Test data prepared:");
        System.out.println("Book ID: " + testBook.getBookId());
        System.out.println("Member ID: " + testMember.getMemberId());
    }

    @Test
    @Order(2)
    void create() {
        Loan created = loanService.create(testLoan);
        assertNotNull(created);
        assertNotNull(created.getLoanId());
        assertEquals(testBook.getBookId(), created.getBook().getBookId());
        assertEquals(testMember.getMemberId(), created.getMember().getMemberId());

        // Update testLoan with the generated ID
        testLoan = created;
        System.out.println("Created Loan: " + created);
    }

    @Test
    @Order(3)
    void read() {
        Loan read = loanService.read(testLoan.getLoanId());
        assertNotNull(read);
        assertEquals(testLoan.getLoanId(), read.getLoanId());
        System.out.println("Read Loan: " + read);
    }

    @Test
    @Order(4)
    void update() {
        // Create an updated version with new return date
        Loan updatedLoan = new Loan.Builder()
                .copy(testLoan)
                .setReturnDate(LocalDate.of(2024, 1, 12))
                .build();

        Loan updated = loanService.update(updatedLoan);
        assertNotNull(updated);
        assertEquals(LocalDate.of(2024, 1, 12), updated.getReturnDate());
        System.out.println("Updated Loan: " + updated);
    }

    @Test
    @Order(5)
    void returnBook() {
        // Test the returnBook method specifically
        Loan returned = loanService.returnBook(testLoan.getLoanId());
        assertNotNull(returned);
        assertNotNull(returned.getReturnDate());
        System.out.println("Returned Loan: " + returned);
    }

    @Test
    @Order(6)
    void delete() {
        boolean deleted = loanService.delete(testLoan.getLoanId());
        assertTrue(deleted);

        // Verify it's actually deleted
        Loan found = loanService.read(testLoan.getLoanId());
        assertNull(found);
        System.out.println("Deleted Loan with ID: " + testLoan.getLoanId());
    }

    @Test
    @Order(7)
    void getAllLoans() {
        // Create another loan to test getAll
        Book anotherBook = BookFactory.createBook(
                543346532335678L,
                "Another Test Book",
                "Another Author",
                true,
                "9780987654321"
        );
        Book savedAnotherBook = bookService.create(anotherBook);

        Loan anotherLoan = LoanFactory.createLoan(
                savedAnotherBook,
                testMember,
                1
        );
        loanService.create(anotherLoan);

        // Test getAllLoans
        java.util.List<Loan> allLoans = loanService.getAllLoans();
        assertNotNull(allLoans);
        assertFalse(allLoans.isEmpty());

        System.out.println("All Loans (" + allLoans.size() + "):");
        allLoans.forEach(loan -> System.out.println("  - " + loan));

        // Clean up the additional loan
        loanService.delete(anotherLoan.getLoanId());
        bookService.delete(savedAnotherBook.getBookId());
    }

    @AfterAll
    static void cleanUp(@Autowired LoanService loanService,
                        @Autowired BookService bookService,
                        @Autowired MemberService memberService) {
        try {
            // Clean up any remaining test data
            java.util.List<Loan> allLoans = loanService.getAllLoans();
            for (Loan loan : allLoans) {
                if (loan.getBook().getTitle().contains("Test")) {
                    loanService.delete(loan.getLoanId());
                }
            }

            java.util.List<Book> allBooks = bookService.getAllBooks();
            for (Book book : allBooks) {
                if (book.getTitle().contains("Test")) {
                    bookService.delete(book.getBookId());
                }
            }

            java.util.List<Member> allMembers = memberService.getAllMembers();
            for (Member member : allMembers) {
                if (member.getName().contains("Test")) {
                    memberService.delete(member.getMemberId());
                }
            }

            System.out.println("Test data cleanup completed");
        } catch (Exception e) {
            System.out.println("Cleanup error: " + e.getMessage());
        }
    }
}