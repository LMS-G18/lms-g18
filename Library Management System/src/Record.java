import java.time.LocalDate;

public class Record {

    private Book book;
    private Member member;
    private LocalDate issueDate = LocalDate.now();
    private LocalDate returnDate = LocalDate.now().plusDays(7);
    private int fine;

    public Record(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public int calculateFine(int overdueDays) {
        int fineAmount = 0;
        if (overdueDays <= 7) {
            fineAmount = overdueDays * 50;
        } else {
            fineAmount = 7 * 50 + (overdueDays - 7) * 100;
        }
        return fineAmount;
    }

    public void viewRecordDetails() {
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Return Date: " + returnDate);

        int overdueDays = LocalDate.now().compareTo(returnDate);
        if (overdueDays > 0) {
            fine = calculateFine(overdueDays);
        }
        System.out.println("Fine: " + fine);
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getFine() {
        return fine;
    }
}
