package password.validator;

import java.util.ArrayList;
import java.util.Iterator;
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
		DisplayPasswordRequirements();
			ResetErrors();
			String password = PromptForPassword();
			ValidatePassword(password);
	}

	// Helper Methods
	private static void DisplayPasswordRequirements() {
		// TODO:PAP - display password requirements
	}

	private static void PresentErrors() {
		Iterator<String> iterator = ValidationErrors.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static String PromptForPassword() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static void ResetErrors() {
		ValidationErrors = new ArrayList();
	}

	private static void ValidatePassword(String password) {
		ValidatePasswordLength(password);

		if(ValidationErrors.size() > 0) {
			PresentErrors();
		}
	}

	private static void ValidatePasswordLength(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			ValidationErrors.add(ERROR_UNDER_MIN);
		}
	}

}
