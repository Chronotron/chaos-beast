package password.validator;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
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
        PasswordValidator.ValidationErrors = null;
    }

    /**
     * Test of main method, of class PasswordValidator.
     */
    @Test
    public void testMain_UnderMinLength() {
        // setup
        String shortPassword = "1234567";
        SetUserInput(shortPassword);

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
    public void testMain_DoesNotContainEnoughDigits() {
        // setup
        String notEnoughDigits = "abcdefghi";
        SetUserInput(notEnoughDigits);

        // pre-conditions
        int count = 0;
        for (int i = 0, len = notEnoughDigits.length(); i < len; i++) {
            if (Character.isDigit(notEnoughDigits.charAt(i))) {
                count++;
            }
        }
        Assert.assertTrue(count < PasswordValidator.MIN_PASSWORD_DIGITS);
        Assert.assertNull(PasswordValidator.ValidationErrors);

        // execute
        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
        Assert.assertEquals(PasswordValidator.ValidationErrors.get(0), PasswordValidator.ERROR_INVALID_DIGITS);
    }

    @Test
    public void testMain_AlphaNumeric() {
        // setup
        String symbolPassword = "abcd1234!";
        SetUserInput(symbolPassword);

        // pre-conditions
        Assert.assertFalse(symbolPassword.matches("[A-Za-z0-9]*"));
        Assert.assertNull(PasswordValidator.ValidationErrors);

        // execute
        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(1, PasswordValidator.ValidationErrors.size());
    }

    @Test
    public void testMain_ValidPassword() {
        // setup
        String symbolPassword = "abcd1234";
        SetUserInput(symbolPassword);

        // pre-conditions
        Assert.assertNull(PasswordValidator.ValidationErrors);

        // execute
        PasswordValidator.main(null);

        // post conditions
        Assert.assertEquals(0, PasswordValidator.ValidationErrors.size());
    }
    // Helper Methods
    private void SetUserInput(String userInput) {
        ByteArrayInputStream testStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testStream);
    }
}