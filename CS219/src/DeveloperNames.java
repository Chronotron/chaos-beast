/***************************************************
 * DeveloperNames.java
 * Paul Parker
 * <p/>
 * Prints a list of first names and last names of
 * each individual in the development department and then
 * creates a random full name
 ****************************************************/
public class DeveloperNames {
    public static void main(String[] args) {
        String[] firstNames = {
                "Dalton",
                "Mittal",
                "Jeff",
                "Jacob",
                "Jaime",
                "Paul",
                "Stan",
                "Carlos",
                "Hank",
                "Steve",
                "Cade",
                "Evan",
                "Mark",
                "Mark",
                "Matt"
        }; // all developer first names
        String[] lastNames = {
                "Shah",
                "Petross",
                "Rauch",
                "Ellis",
                "Parker",
                "Bennett",
                "Sanchez",
                "Van Til",
                "Bannerman",
                "Moorman",
                "Stenmark",
                "Wiegand",
                "Kollasch",
                "Shaner",
                "Wood"
        }; // all developer last names

        int firstNameIndex; // random index for the first name
        int lastNameIndex; // random index for the last name

        // print the first names
        printNames(firstNames, "First Names");
        // print the last names
        printNames(lastNames, "Last Names");

        // get random names from array
        firstNameIndex = (int) (Math.random() * firstNames.length + 1);
        lastNameIndex = (int) (Math.random() * lastNames.length + 1);

        // print a divider
        System.out.println("*****************************");
        System.out.println();

        // print random name
        System.out.printf("%1$s %2$s", firstNames[firstNameIndex], lastNames[lastNameIndex]);
    }

    /**
     * This method prints names with a title to System.out
     * @param names list of names
     * @param title title of this section
     */
    private static void printNames(String[] names, String title) {
        System.out.println(title);
        System.out.println("*****************************");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();
    }

}
