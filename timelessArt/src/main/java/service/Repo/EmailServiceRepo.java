package service.Repo;

public interface EmailServiceRepo {
    void sendPasswordResetEmail(String toEmail, String resetLink);
}
