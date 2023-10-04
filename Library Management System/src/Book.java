public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String subject;

    public Book(int bookId, String bookName, String bookAuthor, String subject) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.subject = subject;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }
    

    public void viewBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Book Author: " + bookAuthor);
        System.out.println("Subject: " + subject);
    }
}
