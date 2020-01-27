package dto;

public class CivilStateDTO {
    private String civilStatus;

    public CivilStateDTO() {
    }

    public CivilStateDTO(String civilStatus) {
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
        return "CivilStateDTO{" +
                "civilStatus='" + civilStatus + '\'' +
                '}';
    }
}
