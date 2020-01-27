package entity;

import java.sql.Date;

public class Notification implements SuperEntity{
    private String senderId;
    private String userId;
    private Date date;
    private String postId;

    public Notification() {
    }

    public Notification(String senderId, String userId, Date date, String postId) {
        this.senderId = senderId;
        this.userId = userId;
        this.date = date;
        this.postId = postId;
    }
    public Notification(String senderId) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "senderId='" + senderId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", postId='" + postId + '\'' +
                '}';
    }
}
