package password.validator;

import java.util.ArrayList;
import java.util.Scanner;

public class PasswordValidator {

	// Constants
	public static String ERROR_UNDER_MIN = "- Invalid Password. Must be at least 8 characters.";

	public static int MIN_PASSWORD_LENGTH = 8;

	// Properties
	public static ArrayList<String> ValidationErrors;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ResetErrors();
		DisplayPasswordRequirements();
		String password = PromptForPassword();
		ValidatePassword(password);
	}

	// Public Methods
	private static void DisplayPasswordRequirements() {
		// TODO:PAP - display password requirements
	}

	private static String PromptForPassword() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static void ValidatePassword(String password) {
		ValidatePasswordLength(password);
	}

	private static void ValidatePasswordLength(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			ValidationErrors.add(ERROR_UNDER_MIN);
		}
	}

	private static void ResetErrors() {
		ValidationErrors = new ArrayList();
	}

}
