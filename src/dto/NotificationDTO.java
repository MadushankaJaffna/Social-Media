package dto;

public class NotificationDTO {
    private String senderId;
    private String userId;
    private String PostId;

    public NotificationDTO() {
    }

    public NotificationDTO(String senderId, String userId, String postId) {
        this.senderId = senderId;
        this.userId = userId;
        PostId = postId;
    }
    public NotificationDTO(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "senderId='" + senderId + '\'' +
                ", userId='" + userId + '\'' +
                ", PostId='" + PostId + '\'' +
                '}';
    }
}
