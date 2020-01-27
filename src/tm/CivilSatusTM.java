package tm;

public class CivilSatusTM {
    private String civilStatus;

    public CivilSatusTM() {
    }

    public CivilSatusTM(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    @Override
    public String toString() {
        return "CivilSatusTM{" +
                "civilStatus='" + civilStatus + '\'' +
                '}';
    }
}
