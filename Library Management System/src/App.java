import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Library library = new Library();
        Boolean loop = true;

        while (loop == true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome to Library");
            System.out.println("Please select an option from below given options");
            System.out.println("1. Add new Member");
            System.out.println("2. Remove a Member");
            System.out.println("3. View Member Details");
            System.out.println("4. View all Members");
            System.out.println("5. Add new Book");
            System.out.println("6. Remove a Book");
            System.out.println("7. View Book Details");
            System.out.println("8. View all Books");
            System.out.println("9. Issue a Book");
            System.out.println("10. Return a Book");
            System.out.println("11. View all Issued Books");
            System.out.println("12. Calculate Fine by days");
            System.out.println("13. Calculate fine for a Book by Book ID");
            System.out.println("14. View all Overdue Books");
            System.out.println("15. View lending details by Book ID");
            System.out.println("16. Exit");
            int mainOption = 0 ;
            try {
                mainOption = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid option");
                scan.next();
                continue;
            }
            switch (mainOption) {
                case 1:
                    try {
                        System.out.println("Enter Member ID");
                        int memberIdToAdd = scan.nextInt();
                        System.out.println("Enter Member Name");
                        String memberNameToAdd = scan.next();
                        Member member = new Member(memberIdToAdd, memberNameToAdd);
                        library.addMember(member);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter Member ID");
                        int MemberIdR = scan.nextInt();
                        library.removeMember(MemberIdR);
                        System.out.println();
                    } catch (Exception e) {
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Enter Member ID");
                        int MemberIdToView = scan.nextInt();
                        library.viewMemberDetails(MemberIdToView);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    library.viewAllMembers();
                    System.out.println();
                    break;
                case 5:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToAdd = scan.nextInt();
                        System.out.println("Enter Book Name");
                        String bookNameToAdd = scan.next();
                        System.out.println("Enter Book Author");
                        String bookAuthorToAdd = scan.next();
                        System.out.println("Enter Book Subject");
                        String bookSubjectToAdd = scan.next();
                        Book book = new Book(bookIdToAdd, bookNameToAdd, bookAuthorToAdd, bookSubjectToAdd);
                        library.addBook(book);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToRemove = scan.nextInt();
                        library.removeBook(bookIdToRemove);
                        System.out.println();
                    } catch (Exception e) {
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToView = scan.nextInt();
                        library.viewBookDetails(bookIdToView);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 8:
                    library.viewAllBooks();
                    System.out.println();
                    break;
                case 9:

                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToIssue = scan.nextInt();
                        System.out.println("Enter Member ID");
                        int memberIdToIssue = scan.nextInt();
                        library.issueBook(bookIdToIssue, memberIdToIssue);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToReturn = scan.nextInt();
                        library.returnBook(bookIdToReturn);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 11:
                    library.viewAllRecords();
                    break;
                case 12:
                    try {
                        System.out.println("Enter number of days Overdue");
                        int daysOverdue = scan.nextInt();
                        System.out.println(library.calculateFine(daysOverdue));
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 13:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToCalculateFine = scan.nextInt();
                        System.out.println(library.calculateFineByBook(bookIdToCalculateFine));
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 14:
                    library.viewAllOverdueRecords();
                    System.out.println();
                    break;
                case 15:
                    try {
                        System.out.println("Enter Book ID");
                        int bookIdToViewRecord = scan.nextInt();
                        library.viewRecordDetails(bookIdToViewRecord);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 16:
                    System.out.println("Exiting the program");
                    System.out.println("Thank You");
                    loop = false;

                    break;
                default:
                    System.out.println("You entered a wrong option");
                    break;
            }
        }
        scan.close();
    }
}
