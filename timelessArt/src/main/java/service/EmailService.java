package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;

    public void sendPasswordResetEmail(String toEmail, String resetLink) {
        System.out.println("Preparing to send email to: " + toEmail);
        System.out.println("Reset link: " + resetLink);
        try {
            mailer.send(Mail.withText(toEmail, "Password Reset Request",
                    "To reset your password, click the link below:\n" + resetLink));
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
