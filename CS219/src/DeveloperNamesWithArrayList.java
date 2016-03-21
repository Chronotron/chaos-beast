import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/****************************************************
 * DeveloperNamesWithArrayList.java
 * Paul Parker
 *
 * Collects a list of first names and last names from
 * the user and then creates a random full name
 ****************************************************/
public class DeveloperNamesWithArrayList {
    public static void main(String[] args) {
        ArrayList<String> firstNames = new ArrayList<String>(); // all developer first names
        ArrayList<String> lastNames = new ArrayList<String>(); // all developer last names
        Random random = new Random(); // random number generator
        int firstNameIndex; // random index for the first name
        int lastNameIndex; // random index for the last name

        // initialize lists with default names
        firstNames.add("John");
        lastNames.add("Doe");

        // print initial names
        printNames(firstNames, "Initial First Names");
        printNames(lastNames, "Initial Last Names");

        // prompt for new names
        promptForNames(firstNames, "Add new first names. Enter 'NEXT' to continue");
        promptForNames(lastNames, "Add new last names. Enter 'NEXT' to continue");

        // get random names from array
        firstNameIndex = random.nextInt(firstNames.size());
        lastNameIndex = random.nextInt(lastNames.size());

        // print a divider
        System.out.println("*****************************");
        System.out.println();

        // print random name
        System.out.printf("Random Name: %1$s %2$s", firstNames.get(firstNameIndex), lastNames.get(lastNameIndex));
    }

    /**
     * This method prints names with a title to System.out
     *
     * @param names list of names
     * @param title title of this section
     */
    private static void printNames(ArrayList<String> names, String title) {
        System.out.println(title);
        System.out.println("*****************************");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();
    }

    /**
     * This method will prompt the user for names and add them
     * to the provided list until a blank entry is provided
     *
     * @param names list to add to
     * @param title title of this section
     */
    private static void promptForNames(ArrayList<String> names, String title) {
        boolean done = false; // condition for when input loop is complete
        Scanner scanner = new Scanner(System.in); // scanner for reading user input
        System.out.println(title);

        // prompt for input until an 'NEXT' is provided
        while (!done) {
            String name = scanner.next(); // potential name to be added
            done = name == null || name.equals("NEXT");
            // if not done add a name and present the new count
            if(!done) {
                names.add(name);
                System.out.println("Number of names so far: " + names.size());
            }
        }
    }

}

