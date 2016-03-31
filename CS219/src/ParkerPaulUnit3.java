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
        userInput = scanner.nextLine();

        // print table header
        System.out.println();
        System.out.printf("%1$-9s%2$-9s%3$-9s%4$-9s%5$-18s%6$-5s%n", "Lower", "Upper", "Initial", "ASCII", "Binary", "Hex");

        // print information about each character
        for (char c : userInput.toCharArray()) {
            System.out.printf("%1$-9s%2$-9s%3$-9s%4$-9d%5$-18s%6$-5s%n", Character.toLowerCase(c), Character.toUpperCase(c), c, (int) c, getBinaryFormat(c), getHexFormat(c));
        }
    }

    /**
     * @param c character to format to binary
     * @return returns a formatted binary string derived from c like '0001 0001'
     */
    private static String getBinaryFormat(char c) {
        String padding = "00000000"; // padding for string
        String unpaddedBinary = Integer.toString((int) c, 2); // unpadded binary
        String paddedBinary = (padding + unpaddedBinary).substring(unpaddedBinary.length()); // binary with padding
        String leftSide = paddedBinary.substring(0, 4); // left side of formatted string
        String rightSide = paddedBinary.substring(4); // right side of formatted string
        return String.format("%s %s", leftSide, rightSide);
    }

    /**
     * @param c character to format to hex
     * @return returns a formatted hex string derived from c
     */
    private static String getHexFormat(char c) {
        return Integer.toString((int) c, 16).toUpperCase();
    }

}
