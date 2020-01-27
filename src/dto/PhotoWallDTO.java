package dto;



import java.io.InputStream;
import java.sql.Date;

public class PhotoWallDTO {
    private String userId;
    private String postId;
    private InputStream image;
    private int react;
    private Date date;

    public PhotoWallDTO() {
    }

    public PhotoWallDTO(String userId, String postId, InputStream image, int react, Date date) {
        this.userId = userId;
        this.postId = postId;
        this.image = image;
        this.react = react;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public int getReact() {
        return react;
    }

    public void setReact(int react) {
        this.react = react;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PhotoWallDTO{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", image=" + image +
                ", react=" + react +
                ", date=" + date +
                '}';
    }
}
