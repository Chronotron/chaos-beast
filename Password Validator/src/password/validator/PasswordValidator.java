package password.validator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	// Constants
	public static String ERROR_UNDER_MIN = "- Invalid Password. Must be at least 8 characters.";
    public static String ERROR_INVALID_CHARACTERS = "- Invalid Password. Cannot contain special characters";
	public static String ERROR_INVALID_DIGITS = "- Invalid Password. Must have at least 2 digits.";

    public static int MIN_PASSWORD_DIGITS = 2;
	public static int MIN_PASSWORD_LENGTH = 8;

	// Properties
	public static ArrayList<String> ValidationErrors;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DisplayPasswordRequirements();
		ResetErrors();
		String password = PromptForPassword();
		ValidatePassword(password);
	}

    // Helper Methods
	private static void DisplayPasswordRequirements() {
		System.out.println("Please enter a valid password.");
        System.out.println("   - Must be at least 8 characters.");
        System.out.println("   - Must have at least 2 digits");
        System.out.println("   - Cannot contain any special characters");
        System.out.println();
	}

	private static void PresentErrors() {
        for (String validationError : ValidationErrors) {
            System.out.println(validationError);
        }
    }

	private static String PromptForPassword() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static void ResetErrors() {
        ValidationErrors = new ArrayList<>();
    }

	private static void ValidatePassword(String password) {
		ValidatePasswordLength(password);
		ValidatePasswordDigits(password);
        ValidatePasswordCharacters(password);

		if(ValidationErrors.size() > 0) {
			PresentErrors();
		} else {
            System.out.println("The password is valid");
        }
        System.out.println();
	}

    private static void ValidatePasswordCharacters(String password) {
        String invalid = "[^A-Za-z0-9]";
        Pattern digitsPattern = Pattern.compile(invalid);
        Matcher matcher = digitsPattern.matcher(password);

        if (matcher.find()) {
            ValidationErrors.add(ERROR_INVALID_CHARACTERS);
        }
    }

	private static void ValidatePasswordLength(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			ValidationErrors.add(ERROR_UNDER_MIN);
		}
	}

	private static void ValidatePasswordDigits(String password) {
        String digits = "[0-9]";
        Pattern digitsPattern = Pattern.compile(digits);
		Matcher matcher = digitsPattern.matcher(password);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }

        if (matches < MIN_PASSWORD_DIGITS) {
            ValidationErrors.add(ERROR_INVALID_DIGITS);
		}
	}

}
