package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Default constructor (required by JPA)
    public Loan() {}

    // Builder constructor
    private Loan(Builder builder) {
        this.loanId = builder.loanId;
        this.book = builder.book;
        this.member = builder.member;
        this.loanDate = builder.loanDate;
        this.dueDate = builder.dueDate;
        this.returnDate = builder.returnDate;
    }

    // Getters
    public Long getLoanId() { return loanId; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    // Setters (required for updating entities)
    public void setLoanId(Long loanId) { this.loanId = loanId; }
    public void setBook(Book book) { this.book = book; }
    public void setMember(Member member) { this.member = member; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    // Builder Pattern
    public static class Builder {
        private Long loanId;
        private Book book;
        private Member member;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private LocalDate returnDate;

        public Builder setLoanId(Long loanId) {
            this.loanId = loanId;
            return this;
        }

        public Builder setBook(Book book) {
            this.book = book;
            return this;
        }

        public Builder setMember(Member member) {
            this.member = member;
            return this;
        }

        public Builder setLoanDate(LocalDate loanDate) {
            this.loanDate = loanDate;
            return this;
        }

        public Builder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder copy(Loan loan) {
            this.loanId = loan.loanId;
            this.book = loan.book;
            this.member = loan.member;
            this.loanDate = loan.loanDate;
            this.dueDate = loan.dueDate;
            this.returnDate = loan.returnDate;
            return this;
        }

        public Loan build() {
            return new Loan(this);
        }
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", book=" + (book != null ? book.getTitle() : "null") +
                ", member=" + (member != null ? member.getName() : "null") +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}