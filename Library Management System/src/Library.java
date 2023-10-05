import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Library {
    List<Book> books = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<Record> records = new ArrayList<>();

    // Book Methods
    public void addBook(Book book) {
        if (books.size() > 0) {
            for (Book b : books) {
                if (b.getBookId() == book.getBookId()) {
                    System.out.println("Book with same ID already exists");
                    return;
                }
            }
        }
        books.add(book);
        System.out.println("Book added successfully");
    }

    public void removeBook(int bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                found = true;
                System.out.println("Book removed successfully");
            }
        }
        if (found == false) {
            System.out.println("Book not found in library");
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
            System.out.println("Book not found in library");
        }
    }

    public void viewAllBooks() {
        for (Book book : books) {
            book.viewBookDetails();
            System.out.println();
        }
        if (books.size() == 0) {
            System.out.println("No books found in library");
        }
    }

    // Member Methods
    public void addMember(Member member) {
        if (members.size() > 0) {
            for (Member m : members) {
                if (m.getMemberId() == member.getMemberId()) {
                    System.out.println("Member with same ID already exists");
                    System.out.println("try again with different ID");
                    return;
                }
            }
        }
        members.add(member);
        System.out.println("Member added successfully");
    }

    public void removeMember(int memberId) {
        boolean found = false;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                members.remove(member);
                System.out.println("Member removed successfully");
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Registered member not found");
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
            System.out.println("Registered member not found");
        }
    }

    public void viewAllMembers() {
        for (Member member : members) {
            member.viewMemberDetails();
            System.out.println();
        }
        if (members.size() == 0) {
            System.out.println("No registered members found");
        }
    }

    // Record Methods

    public void issueBook(int bookId, int memberId) {
        boolean bookFound = false;
        boolean memberFound = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookFound = true;
                if (book.getIsIssued() == false) {
                    for (Member member : members) {
                        if (member.getMemberId() == memberId) {
                            memberFound = true;
                            Record record = new Record(book, member);
                            records.add(record);
                            book.setIssued(true);
                            System.out.println("Book issued successfully");
                        }
                    }
                } else {
                    System.out.println("Book already issued");
                    return;
                }
            }
        }

        if (bookFound == false) {
            System.out.println("Book not found in library");
        } else if (memberFound == false) {
            System.out.println("Registered member not found");
        }
    }

    public void returnBook(int bookId) {
        boolean found = false;
        boolean bookFoundInLibrary = false;
        int fine = 0;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookFoundInLibrary = true;
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
            }
        }
        if (bookFoundInLibrary == false) {
            System.out.println("Book not found in library");
        } else if (found == false) {
            System.out.println("Book not found in issued records");
        }
    }

    public void viewRecordDetails(int bookId) {
        boolean found = false;
        boolean bookFoundInLibrary = false;

        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookFoundInLibrary = true;
                for (Record record : records) {
                    if (record.getBook().getBookId() == bookId) {
                        found = true;
                        record.viewRecordDetails();
                    }
                }
            }
        }
        if (bookFoundInLibrary == false) {
            System.out.println("Book not found in library");
        } else if (found == false) {
            System.out.println("Book not found in issued records");
        }
    }

    public void viewAllRecords() {
        for (Record record : records) {
            record.viewRecordDetails();
            System.out.println();
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
                        System.out.println();
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
                System.out.println();
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
            System.out.println("Book not found in library");
        }
        return fineAmount;
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // just for demonstration purposes
    public void changeIssuedDate(int bookId, LocalDate newIssuedDate) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Invalid book ID");
            return;
        }
        LocalDate newReturnDate = newIssuedDate.plusDays(7);
        for (Record record : records) {
            if (record.getBook().getBookId() == bookId) {
                record.setIssuedDate(newIssuedDate);
                record.setReturnDate(newReturnDate);
            }
        }
        System.out.println("Issued date updated successfully");
    }
}