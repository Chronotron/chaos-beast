import java.util.Arrays;

/***************************************************
 * ParkerPaulUnit1.java
 * Paul Parker
 *
 * Prints unsorted and sort arrays of random numbers
 * and asks user for number to search for
 ****************************************************/
public class ParkerPaulUnit1 {
    public static void main(String[] args) {
        int[] unsortedArray; // array to hold unsorted random numbers
        int[] arrayCopy; // copy of the unsorted array
        int arrayLength = 10; // length of arrays for this exercise
        int minNumber = 1; // lower bound of random number
        int maxNumber = 20; // upper bound of random number

        // initialize the array with random numbers
        unsortedArray = new int[arrayLength];
        for (int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = (int) (Math.random() * maxNumber + minNumber);
        }

        // copy unsorted array
        arrayCopy = Arrays.copyOf(unsortedArray, unsortedArray.length);
        // sort the array
        Arrays.sort(arrayCopy);

        // TODO:PAP - print table of both arrays
    }
}
