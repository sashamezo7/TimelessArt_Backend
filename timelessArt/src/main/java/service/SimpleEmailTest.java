package service;

import exception.InvalidCredentialsException;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimpleEmailTest {

    @Inject
    Mailer mailer;

    public void sendTestEmail() {
        try {
            mailer.send(Mail.withText("anghelutaruxandra88@gmail.com", "Test Email",
                    "This is a test email to verify SMTP settings."));
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
