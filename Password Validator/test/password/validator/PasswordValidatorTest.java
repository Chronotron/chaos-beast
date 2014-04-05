package password.validator;

import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Chrontron2
 */
public class PasswordValidatorTest {

	public PasswordValidatorTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		SetUserInput("");
	}

	/**
	 * Test of main method, of class PasswordValidator.
	 */
	@Test
	public void testMain_UnderMinLength() {
		// setup
		String shortPassword = "1234567";
		SetUserInput(shortPassword);
		String[] args = null;

		// pre-conditions
		Assert.assertTrue(shortPassword.length() < PasswordValidator.MIN_PASSWORD_LENGTH);
		Assert.assertNull(PasswordValidator.ValidationErrors);

		// execute
		PasswordValidator.main(args);

		// post conditions
		Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
		Assert.assertEquals(PasswordValidator.ValidationErrors.get(0), PasswordValidator.ERROR_UNDER_MIN);
	}

	@Test
	public void testMain_DoesNotContaintEnoughDigits() {
		// setup
		String notEnoughDigits = "abcdefghi";
		SetUserInput(notEnoughDigits);
		String[] args = null;

		// pre-conditions
		// TODO:PAP - figure this out
//		Assert.assertTrue(notEnoughDigits.length() < PasswordValidator.MIN_PASSWORD_LENGTH);
		Assert.assertNull(PasswordValidator.ValidationErrors);

		// execute
		PasswordValidator.main(args);

		// post conditions
		Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
		Assert.assertEquals(PasswordValidator.ValidationErrors.get(0), PasswordValidator.ERROR_INVALID_DIGITS);
	}

	// Helper Methods
	private void SetUserInput(String userInput) {
		ByteArrayInputStream testStream = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(testStream);
	}

}
