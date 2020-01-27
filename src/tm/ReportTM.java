package tm;

public class ReportTM {
    private String senderId;
    private String blockerId;

    public ReportTM() {
    }

    public ReportTM(String senderId, String blockerId) {
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
        return "ReportTM{" +
                "senderId='" + senderId + '\'' +
                ", BlockerId='" + blockerId + '\'' +
                '}';
    }
}
