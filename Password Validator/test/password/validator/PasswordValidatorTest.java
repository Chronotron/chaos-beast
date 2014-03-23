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
	}

	/**
	 * Test of main method, of class PasswordValidator.
	 */
	@Test
	public void testMain_UnderMinLength() {
		// setup
		String shortPassword = "1234567";
		ByteArrayInputStream testStream = new ByteArrayInputStream(shortPassword.getBytes());
		System.setIn(testStream);
		System.out.println("main - Password Under Min Length");
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

}
