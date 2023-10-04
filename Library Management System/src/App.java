import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Library library = new Library();
        Boolean loop = true;

        while (loop == true) {
            System.out.println("Welcome to Library");
            System.out.println("Please select an option from below given options");
            System.out.println("1. Member Operations");
            System.out.println("2. Book Operations");
            System.out.println("3. Lending Operations");
            System.out.println("4. Exit");
            int mainOption = scan.nextInt();
            switch (mainOption) {
                case 1:
                    System.out.println("You selected Member Operations");
                    break;
                case 2:
                    System.out.println("You selected Book Operations");
                    break;
                case 3:
                    System.out.println("You selected Lending Operations");
                    break;
                case 4:
                    System.out.println("Exiting the program");
                    System.out.println("Thank You");
                    loop = false;
                    break;
                default:
                    System.out.println("You entered a wrong option");
                    break;
            }

        }
    }
}
