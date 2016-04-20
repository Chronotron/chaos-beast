import java.util.Scanner;

/***************************************************
 * ParkerPaulUnit6.java
 * Paul Parker
 * <p>
 * class demonstrating polymorphism
 ****************************************************/
public class ParkerPaulUnit6 {
    public static void main(String[] args) {
        String userInput = ""; // input from the user
        String quitCode = "q"; // code for quitting program
        Scanner scanner = new Scanner(System.in); // scanner for user input
        ParkerPaulTime time; // time object for display
        while (!userInput.equalsIgnoreCase(quitCode)) {
            System.out.print("Enter time in the form hh:mm (\"q\" to quit): ");
            userInput = scanner.next();
            if(!userInput.equalsIgnoreCase(quitCode)) {
                time = new ParkerPaulTime(userInput);
                time.print();
            }
        }
    }
}
