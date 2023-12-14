package service;

public interface IRegisterWithEmailOTPService {
    public boolean registerUser(String name, String email, String password,String phone , String address);
    public boolean sendOTP(String email);
    public boolean verifyOTP(String email, String otp);
}


