package dto;

public class LoginDTO {
    private String userId;
    private String email;
    private String password;
    private boolean roll;

    public LoginDTO() {
    }

    public LoginDTO(String userId, String email, String password, boolean roll) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roll = roll;
    }
    public LoginDTO(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRoll() {
        return roll;
    }

    public void setRoll(boolean roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roll=" + roll +
                '}';
    }
}
