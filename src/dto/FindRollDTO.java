package dto;

public class FindRollDTO {
    private String userId;
    private Boolean roll;

    public FindRollDTO(String userId, Boolean roll) {
        this.userId = userId;
        this.roll = roll;
    }

    public FindRollDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getRoll() {
        return roll;
    }

    public void setRoll(Boolean roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "FindRollDTO{" +
                "userId='" + userId + '\'' +
                ", roll=" + roll +
                '}';
    }
}
