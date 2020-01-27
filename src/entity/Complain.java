package entity;

public class Complain implements SuperEntity{
    private String senderId;
    private String blockerId;

    public Complain() {
    }

    public Complain(String senderId, String blockerId) {
        this.senderId = senderId;
        this.blockerId = blockerId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Complain{" +
                "senderId='" + senderId + '\'' +
                ", blockerId='" + blockerId + '\'' +
                '}';
    }

    public String getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(String blockerId) {
        this.blockerId = blockerId;
    }
}
