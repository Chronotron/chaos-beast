import java.util.Scanner;

/***************************************************
 * ParkerPaulUnit3.java
 * Paul Parker
 * <p>
 * Gets user input and outputs each character as
 * various types
 ****************************************************/
public class ParkerPaulUnit3 {
    public static void main(String[] args) {
        Scanner scanner; // scanner for receiving user input
        String userInput; // input received from user

        // initialize scanner and receive input
        scanner = new Scanner(System.in);
        System.out.print("Please enter a string of any length: ");
        // TODO:PAP - don't leave this
//        userInput = scanner.nextLine();

        userInput = "asdf";
        // print table header
        System.out.println();
        System.out.printf("%1$-9s%2$-9s%3$-9s%4$-9s%5$-18s%6$-5s%n", "Lower", "Upper", "Initial", "ASCII", "Binary", "Hex");

        for (char c : userInput.toCharArray()) {
            // TODO:PAP - hex formatting
            System.out.printf("%1$-9s%2$-9s%3$-9s%4$-9d%5$-18s%6$-5s%n",  Character.toLowerCase(c), Character.toUpperCase(c), c, (int)c, formatBinary(c), c);
        }
    }

    /**
     *
     * @param c character to format to binary
     * @return returns a formatted binary string derived from c like '0001 0001'
     */
    private static String formatBinary(char c) {
        String unformattedBinary = Integer.toString((int)c, 2); // unformatted binary
        int leftSide = Integer.parseInt(unformattedBinary.substring(0, 4), 10);
        int rightside = Integer.parseInt(unformattedBinary.substring(4), 10);
        return String.format("%04d %04d", leftSide, rightside);
    }

}
