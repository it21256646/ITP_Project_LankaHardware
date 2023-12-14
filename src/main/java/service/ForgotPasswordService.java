package service;

public interface ForgotPasswordService {
    boolean sendPasswordResetLink(String email);
}
