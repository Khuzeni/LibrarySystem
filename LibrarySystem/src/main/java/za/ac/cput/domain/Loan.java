package za.ac.cput.domain;

import java.util.Date;;

public class Loan {
    private String loanId;
    private String bookId;
    private String memberId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public Loan(String loanId, String bookId, String memberId, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public String getLoanId() { return loanId; }
    public String getBookId() { return bookId; }
    public String getMemberId() { return memberId; }
    public Date getLoanDate() { return loanDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }

    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "Loan{" + "loanId='" + loanId + '\'' + ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' + ", loanDate=" + loanDate +
                ", dueDate=" + dueDate + ", returnDate=" + returnDate + '}';
    }
}
