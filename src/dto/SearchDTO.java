package dto;

public class SearchDTO {
    private String userID;
    private String name;

    public SearchDTO() {
    }

    public SearchDTO(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
