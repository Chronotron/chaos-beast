import java.util.Arrays;
import java.util.Scanner;

/***************************************************
 * ParkerPaulUnit1.java
 * Paul Parker
 * <p/>
 * Prints unsorted and sorted arrays of random numbers
 * and asks user for number to search for
 ****************************************************/
public class ParkerPaulUnit1 {
    public static void main(String[] args) {
        int[] unsortedArray; // array to hold unsorted random numbers
        int[] arrayCopy; // copy of the unsorted array
        int arrayLength = 10; // length of arrays for this exercise
        int minNumber = 1; // lower bound of random number
        int maxNumber = 20; // upper bound of random number
        Scanner scanner; // scanner for receiving user input
        int numberToSearchFor; // number to be entered by user to search for

        // initialize the array with random numbers
        unsortedArray = new int[arrayLength];
        for (int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = (int) (Math.random() * maxNumber + minNumber);
        }

        // copy unsorted array
        arrayCopy = Arrays.copyOf(unsortedArray, unsortedArray.length);
        // sort the array
        Arrays.sort(arrayCopy);

        // print formatted table of sorted and unsorted arrays
        System.out.println("Unsorted Array      Sorted Array");
        for (int i = 0; i < arrayLength; i++) {
            System.out.printf("%1$-20d%2$-20d%n", unsortedArray[i], arrayCopy[i]);
        }

        // prompt for a number to search for
        scanner = new Scanner(System.in);
        System.out.print("Please enter number to search for: ");
        numberToSearchFor = scanner.nextInt();

        // search each array and output results
        searchArray(unsortedArray, numberToSearchFor, "unsorted array");
        searchArray(arrayCopy, numberToSearchFor, "sorted array");
    }

    /**
     * searches an array for a given value and outputs the found indexes or not found
     *
     * @param arrayToSearch    array being searched
     * @param valueToSearchFor value to find in arrayToSearch
     * @param arrayName        name of the array to display
     */
    private static void searchArray(int[] arrayToSearch, int valueToSearchFor, String arrayName) {
        Boolean found = false;
        int currentIndex = 0;
        for (int value : arrayToSearch) {
            if (value == valueToSearchFor) {
                System.out.printf("Search Value: %1$d found at location: %2$d in the %3$s%n", valueToSearchFor, currentIndex, arrayName);
                found = true;
            }
            currentIndex++;
        }

        if(!found) {
            System.out.printf("Search Value: %1$d was not found%n", valueToSearchFor);
        }
    }
}
