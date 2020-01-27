package dto;


import java.io.InputStream;
import java.sql.Date;



public class UserDTO {
    private String userID;
    private String name;
    private String gender;
    private String email;
    private int phoneNo;
    private String civilStatus;
    private String description;
    private InputStream proPic;
    private String address;
    private String homeTown;
    private Date birthDay;
    private String working;
    private String education;

    public UserDTO() {
    }

    public UserDTO(String userID, String name, String gender, String email, int phoneNo, String civilStatus, String description, InputStream proPic, String address, String homeTown, Date birthDay, String working, String education) {
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNo = phoneNo;
        this.civilStatus = civilStatus;
        this.description = description;
        this.proPic = proPic;
        this.address = address;
        this.homeTown = homeTown;
        this.birthDay = birthDay;
        this.working = working;
        this.education = education;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getProPic() {
        return proPic;
    }

    public void setProPic(InputStream proPic) {
        this.proPic = proPic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", civilStatus='" + civilStatus + '\'' +
                ", description='" + description + '\'' +
                ", proPic=" + proPic +
                ", address='" + address + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", birthDay=" + birthDay +
                ", working='" + working + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}
