package service;

import model.User;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final IUserService userService;

    public ForgotPasswordServiceImpl(IUserService  userService) {
        this.userService = userService;
    }

    @Override
    public boolean sendPasswordResetLink(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        // Send the password reset link to the user's email
        return true;
    }
}
