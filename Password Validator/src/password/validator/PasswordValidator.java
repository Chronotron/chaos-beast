package password.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	// Constants
	public static String ERROR_UNDER_MIN = "- Invalid Password. Must be at least 8 characters.";
	public static String ERROR_INVALID_DIGITS = "- Invalid Password. Must have at least 2 digits.";

	public static int MIN_PASSWORD_LENGTH = 8;

	// Properties
	public static ArrayList<String> ValidationErrors;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ResetErrors();
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
		ValidatePasswordDigits(password);

		if(ValidationErrors.size() > 0) {
			PresentErrors();
		}
	}

	private static void ValidatePasswordLength(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			ValidationErrors.add(ERROR_UNDER_MIN);
		}
	}

	private static void ShowUserSomeGarbage() {
		
	}

	private static void ValidatePasswordDigits(String password) {
		String digits = "[0-9]{2,}";
		Pattern digitsPattern = Pattern.compile(digits);
		Matcher matcher = digitsPattern.matcher(password);
		if(!matcher.matches()){
			ValidationErrors.add(ERROR_INVALID_DIGITS);
		}
	}

}
