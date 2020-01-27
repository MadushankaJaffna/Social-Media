package entity;

import java.sql.Date;

public class WallPost implements SuperEntity {
    private String postId;
    private int reactCount;
    private String userId;
    private String descriotion;
    private Date date;

    public WallPost() {
    }

    public WallPost(String postId, int reactCount, String userId, String descriotion, Date date) {
        this.postId = postId;
        this.reactCount = reactCount;
        this.userId = userId;
        this.descriotion = descriotion;
        this.date = date;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getReactCount() {
        return reactCount;
    }

    public void setReactCount(int reactCount) {
        this.reactCount = reactCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescriotion() {
        return descriotion;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WallPost{" +
                "postId='" + postId + '\'' +
                ", reactCount=" + reactCount +
                ", userId='" + userId + '\'' +
                ", descriotion='" + descriotion + '\'' +
                ", date=" + date +
                '}';
    }
}
