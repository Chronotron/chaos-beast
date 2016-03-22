import java.util.*;

/***************************************************
 * ParkerPaulUnit1.java
 * Paul Parker
 * <p>
 * Prints unsorted and sorted arrays of random numbers
 * and asks user for number to search for
 ****************************************************/
public class ParkerPaulUnit2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayListCopy; // copy of the unsorted array
        ArrayList<Integer> unsortedArrayList; // array to hold unsorted random numbers
        int itemCount = 10; // desired count of items in lists
        int maxNumber = 20; // upper bound of random number
        int minNumber = 1; // lower bound of random number
        int numberToSearchFor; // number to be entered by user to search for
        Random random = new Random(); // random number generator
        Scanner scanner; // scanner for receiving user input

        // initialize the list with random numbers
        unsortedArrayList = new ArrayList<Integer>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            unsortedArrayList.add(random.nextInt(maxNumber) + minNumber);
        }

        // copy unsorted list
        arrayListCopy = new ArrayList<Integer>(unsortedArrayList);
        // sort the list
        Collections.sort(arrayListCopy);

        // print formatted table of sorted and unsorted lists
        System.out.println("Unsorted List       Sorted List");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%1$-20d%2$-20d%n", unsortedArrayList.get(i), arrayListCopy.get(i));
        }

        // prompt for a number to search for
        scanner = new Scanner(System.in);
        System.out.print("Please enter number to search for: ");
        numberToSearchFor = scanner.nextInt();

        // search each array and output results
        boolean found = searchArrayList(unsortedArrayList, numberToSearchFor, "unsorted list");

        // search sorted list if found in unsorted list
        if (found) {
            searchArrayList(arrayListCopy, numberToSearchFor, "sorted list");
        }
    }

    /**
     * searches an ArrayList for a given value and outputs the found indexes or not found
     *
     * @param arrayListToSearch ArrayList being searched
     * @param valueToSearchFor  value to find in arrayListToSearch
     * @param arrayListName     name of the ArrayList to display
     */
    private static boolean searchArrayList(ArrayList<Integer> arrayListToSearch, int valueToSearchFor, String arrayListName) {
        Boolean found = false; // track if value is ever found
        int currentLocation = 1; // track location since we are iterating a list

        // required foreach loop to search the array and output information if value is found
        for (int value : arrayListToSearch) {
            if (value == valueToSearchFor) {
                System.out.printf("Search Value: %1$d found at location: %2$d in the %3$s%n", valueToSearchFor, currentLocation, arrayListName);
                found = true;
            }
            currentLocation++;
        }

        if (!found) {
            System.out.printf("Search Value: %1$d was not found%n", valueToSearchFor);
        }

        return found;
    }
}
