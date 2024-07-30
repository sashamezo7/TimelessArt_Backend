package validation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    public void testValidPasswords() {
        assertTrue(PasswordValidator.isValid("Password1!"));
        assertTrue(PasswordValidator.isValid("A1b2C3d4!"));
        assertTrue(PasswordValidator.isValid("Strong#Password1"));
        assertTrue(PasswordValidator.isValid("Valid$Password123"));
        assertTrue(PasswordValidator.isValid("Sasamezzo7"));
        assertTrue(PasswordValidator.isValid("Sasamezzo"));

    }

    @Test
    public void testInvalidPasswords() {
        assertFalse(PasswordValidator.isValid("Pass1"));
        assertFalse(PasswordValidator.isValid("12345678"));
        assertFalse(PasswordValidator.isValid(" "));
        assertFalse(PasswordValidator.isValid("         "));
        assertFalse(PasswordValidator.isValid("sasaestecelmaitare"));
        assertFalse(PasswordValidator.isValid("sasaestecelmaitare1"));
        assertFalse(PasswordValidator.isValid("sasaestecelma!itare1"));


    }

}
