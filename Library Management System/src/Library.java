import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Library {
    List<Book> books = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<Record> records = new ArrayList<>();

    // Book Methods
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Book not found");
        }
    }

    public void viewBookDetails(int bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.viewBookDetails();
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Book not found");
        }
    }

    public void viewAllBooks() {
        for (Book book : books) {
            book.viewBookDetails();
        }
        if (books.size() == 0) {
            System.out.println("No books found");
        }
    }

    // Member Methods
    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(int memberId) {
        boolean found = false;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                members.remove(member);
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Member not found");
        }
    }

    public void viewMemberDetails(int memberId) {
        boolean found = false;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                member.viewMemberDetails();
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Member not found");
        }
    }

    public void viewAllMembers() {
        for (Member member : members) {
            member.viewMemberDetails();
        }
        if (members.size() == 0) {
            System.out.println("No members found");
        }
    }

    // Record Methods

    public void issueBook(int bookId, int memberId) {
        boolean bookFound = false;
        boolean memberFound = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookFound = true;
                for (Member member : members) {
                    if (member.getMemberId() == memberId) {
                        memberFound = true;
                        Record record = new Record(book, member);
                        records.add(record);
                        book.setIssued(true);
                        System.out.println("Book issued successfully");
                    }
                }
            }
        }

        if (bookFound == false) {
            System.out.println("Book not found");
        } else if (memberFound == false) {
            System.out.println("Member not found");
        }
    }

    public void returnBook(int bookId) {
        boolean found = false;
        int fine = 0;
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                found = true;
                record.setFine(calculateFineByBook(bookId));
                fine = record.getFine();
                System.out.println("Fine: " + fine);
                record.getBook().setIssued(false);
                records.remove(record);
                System.out.println("Book returned successfully");
            }
        }
        if (found == false) {
            System.out.println("Book not found");
        }
    }

    public void viewRecordDetails(int bookId) {
        boolean found = false;
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                found = true;
                record.viewRecordDetails();
            }
        }
        if (found == false) {
            System.out.println("Book not found");
        }
    }

    public void viewAllRecords() {
        for (Record record : records) {
            record.viewRecordDetails();
        }
        if (records.size() == 0) {
            System.out.println("No records found");
        }
    }

    public void viewAllRecordsByMember(int memberId) {
        boolean memberFound = false;
        boolean recordFound = false;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                memberFound = true;
                for (Record record : records) {
                    if (record.getMember().getMemberId() == memberId) {
                        record.viewRecordDetails();
                        recordFound = true;
                    }
                }
            }
        }
        if (memberFound == false) {
            System.out.println("Member not found");
        } else if (recordFound == false) {
            System.out.println("No records found");
        }
    }

    public void viewAllOverdueRecords() {
        boolean overdueRecordFound = false;
        for (Record record : records) {
            if (record.getFine() > 0) {
                record.viewRecordDetails();
                System.out.println();
                overdueRecordFound = true;
            }
        }
        if (overdueRecordFound == false) {
            System.out.println("No overdue records found");
        }
    }

    public void viewAllOverdueRecordsByMember(int memberId) {
        boolean memberFound = false;
        boolean overdueRecordFound = false;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                memberFound = true;
                for (Record record : records) {
                    if (record.getMember().getMemberId() == memberId && record.getFine() > 0) {
                        record.viewRecordDetails();
                        System.out.println();
                        overdueRecordFound = true;
                    }
                }
            }
        }
        if (memberFound == false) {
            System.out.println("Member not found");
        } else if (overdueRecordFound == false) {
            System.out.println("No overdue records found for this member");
        }
    }

    public int calculateFine(int overdueDays) {
        int fineAmount = 0;
        try {
            if (overdueDays <= 7) {
                fineAmount = overdueDays * 50;
            } else {
                fineAmount = 7 * 50 + (overdueDays - 7) * 100;
            }
            return fineAmount;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    public int calculateFineByBook(int bookId) {
        int fineAmount = 0;
        int bookFound = 0;
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                bookFound = 1;
                int overdueDays = LocalDate.now().compareTo(record.getReturnDate());
                if (overdueDays > 0) {
                    fineAmount = calculateFine(overdueDays);
                }
            }
        }
        if (bookFound == 0) {
            System.out.println("Book not found");
        }
        return fineAmount;
    }

}
