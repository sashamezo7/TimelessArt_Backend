package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private PasswordValidator() {
        throw new UnsupportedOperationException("The class cannot be instantiated");
    }

    public static boolean isValid(String password) {
        // Corrected regex pattern without the newline character
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
