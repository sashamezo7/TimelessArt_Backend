package validation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class EmailValidatorTest {

    @Test
    public void testValidEmails() {
        assertTrue(EmailValidator.isValid("test@example.com"));
        assertTrue(EmailValidator.isValid("user.name@domain.co.in"));
        assertTrue(EmailValidator.isValid("user_name@domain.com"));
        assertTrue(EmailValidator.isValid("user-name+tag@domain.org"));
        assertTrue(EmailValidator.isValid("user123@domain123.com"));
        assertTrue(EmailValidator.isValid("mezo.ioan.alexandru@gmail.com"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(EmailValidator.isValid("username@.com"));
        assertFalse(EmailValidator.isValid("username@domain"));
        assertFalse(EmailValidator.isValid("@domain.com"));
        assertFalse(EmailValidator.isValid("user@domain..com"));
        assertFalse(EmailValidator.isValid("user@domain_com"));
        assertFalse(EmailValidator.isValid("user@domain,com"));
        assertFalse(EmailValidator.isValid("user@domain space.com"));
        assertFalse(EmailValidator.isValid("user@-domain.com"));
        assertFalse(EmailValidator.isValid("user@domain-.com"));
        assertFalse(EmailValidator.isValid("user@domain..com"));
        assertFalse(EmailValidator.isValid(" sasamezo77@gmail.com"));
        assertFalse(EmailValidator.isValid("sasamezo77 @gmail.com"));
        assertFalse(EmailValidator.isValid("sasamezo77@ gmail.com"));
        assertFalse(EmailValidator.isValid("sasamezo77 @ gmail.com"));
        assertFalse(EmailValidator.isValid("sasamezo77@ gmail.com "));
        assertFalse(EmailValidator.isValid("sasamezo77@ gmail . com "));
        assertFalse(EmailValidator.isValid(" "));
        assertFalse(EmailValidator.isValid("       "));


    }
}
