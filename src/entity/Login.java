package entity;

public class Login implements SuperEntity{
    private String uid;
    private String email;
    private String password;
    private boolean roll;

    public Login() {
    }

    public Login(String uid, String email, String password, boolean roll) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.roll = roll;
    }
    public Login(String uid,boolean roll) {
        this.uid = uid;
        this.roll = roll;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return "Login{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roll=" + roll +
                '}';
    }
}
