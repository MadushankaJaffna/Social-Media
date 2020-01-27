package dto;

import entity.SuperEntity;

public class ComplainDTO implements SuperEntity{
    private String senderId;
    private String blockerId;

    public ComplainDTO() {
    }

    public ComplainDTO(String senderId, String blockerId) {
        this.senderId = senderId;
        this.blockerId = blockerId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(String blockerId) {
        this.blockerId = blockerId;
    }

    @Override
    public String toString() {
        return "ComplainDTO{" +
                "senderId='" + senderId + '\'' +
                ", blockerId='" + blockerId + '\'' +
                '}';
    }
}
