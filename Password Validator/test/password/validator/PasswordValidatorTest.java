package password.validator;

import org.junit.*;

import java.io.ByteArrayInputStream;

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
        setUserInput("");
        PasswordValidator.ValidationErrors = null;
    }

    @Test
    public void testMain_UnderMinLength() {
        // setup
        String shortPassword = "1234567";
        setUserInput(shortPassword);
        System.out.println("Test Password Under Min Length");

        // pre-conditions
        Assert.assertTrue(shortPassword.length() < PasswordValidator.MIN_PASSWORD_LENGTH);
        Assert.assertNull(PasswordValidator.ValidationErrors);

		// execute
        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
        Assert.assertEquals(PasswordValidator.ValidationErrors.get(0), PasswordValidator.ERROR_UNDER_MIN);
    }

    @Test
    public void testMain_DoesNotContaintEnoughDigits() {
        // setup
        String notEnoughDigits = "abcdefghi";
        setUserInput(notEnoughDigits);

        // pre-conditions
        // TODO:PAP - figure this out
//		Assert.assertTrue(notEnoughDigits.length() < PasswordValidator.MIN_PASSWORD_LENGTH);
        Assert.assertNull(PasswordValidator.ValidationErrors);

        // execute
        System.out.println("Test Not Enough Digits");
        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
        Assert.assertEquals(PasswordValidator.ValidationErrors.get(0), PasswordValidator.ERROR_INVALID_DIGITS);
    }

    @Test
    public void testMain_PerfectPassword() {
        // setup
        String shortPassword = "abcd1234";
        setUserInput(shortPassword);

        // pre-conditions
        Assert.assertTrue(shortPassword.length() >= PasswordValidator.MIN_PASSWORD_LENGTH);
        Assert.assertNull(PasswordValidator.ValidationErrors);

        // execut
        System.out.println("Test Valid Password");

        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(0, PasswordValidator.ValidationErrors.size());
    }

	// Helper Methods
	private void setUserInput(String userInput) {
		ByteArrayInputStream testStream = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(testStream);
	}
}
