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
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
            }
        }
    }

    public void viewBookDetails(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.viewBookDetails();
            }
        }
    }

    public void viewAllBooks() {
        for (Book book : books) {
            book.viewBookDetails();
        }
    }

    // Member Methods
    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                members.remove(member);
            }
        }
    }

    public void viewMemberDetails(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                member.viewMemberDetails();
            }
        }
    }

    public void viewAllMembers() {
        for (Member member : members) {
            member.viewMemberDetails();
        }
    }

    // Record Methods
    public void issueBook(int bookId, int memberId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                for (Member member : members) {
                    if (member.getMemberId() == memberId) {
                        Record record = new Record(book, member);
                        records.add(record);
                    }
                }
            }
        }
    }

    public void returnBook(int bookId) {
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                records.remove(record);
            }
        }
    }

    public void viewRecordDetails(int bookId) {
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                record.viewRecordDetails();
            }
        }
    }

    public void viewAllRecords() {
        for (Record record : records) {
            record.viewRecordDetails();
        }
    }

    public void viewAllRecordsByMember(int memberId) {
        for (Record record : records) {
            if (record.getMember().getMemberId() == memberId) {
                record.viewRecordDetails();
            }
        }
    }


    public void viewAllOverdueRecords() {
        for (Record record : records) {
            if (record.getFine() > 0) {
                record.viewRecordDetails();
                System.out.println();
            }
        }
    }

    
    public void viewAllOverdueRecordsByMember(int memberId) {
        for (Record record : records) {
            if (record.getMember().getMemberId() == memberId && record.getFine() > 0) {
                record.viewRecordDetails();
            }
        }
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

    public int calculateFineByBook(int bookId) {
        int fineAmount = 0;
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                int overdueDays = LocalDate.now().compareTo(record.getReturnDate());
                if (overdueDays > 0) {
                    fineAmount = calculateFine(overdueDays);
                }
            }
        }
        return fineAmount;
    }

}
