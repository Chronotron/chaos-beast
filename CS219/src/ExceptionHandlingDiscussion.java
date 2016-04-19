import java.util.Scanner;

/***************************************************
 * ExceptionHandlingDiscussion.java
 * Paul Parker
 * <p>
 * Demonstrates exception handling
 ****************************************************/
public class ExceptionHandlingDiscussion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // scanner for receiving user input
        MockOrmWriter dbSession = new MockOrmWriter(); // mock ORM writer
        MockUser userToAdd; // user to add for this exercise;

        // prompt for a user name and create the user
        System.out.println("Please enter a user name:");
        userToAdd = new MockUser(scanner.next());
        try {
            // attempt to open a session and add a user
            dbSession.open();
            dbSession.add(userToAdd);
            System.out.printf("User '%1$s' successfully added%n", userToAdd.getName());
        } catch (ProhibitedValueException ex) {
            // present a friendlier message to user
            System.out.println("Attempted to add a prohibited value: " + ex.getMessage());
        } finally {
            // ensure un-managed resource is always closed regardless of result
            dbSession.close();
        }
    }

} // end class ExceptionHandlingDiscussion

class ProhibitedValueException extends Exception {

    ProhibitedValueException() {
        super("Error code 923742389");
    }

} // end class ProhibitedValueException

class MockOrmWriter {

    void close() {
        System.out.println("Closing an un-managed resource");
    }

    void open() {
        System.out.println("Opening an un-managed resource");
    }

    void add(MockUser user) throws ProhibitedValueException {
        if (user.getName().equalsIgnoreCase("PROHIBITED")) {
            throw new ProhibitedValueException();
        }
    }

} // end class MockOrmWriter

class MockUser {
    private String name; // name of the user

    MockUser(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

} //end class MockUser

