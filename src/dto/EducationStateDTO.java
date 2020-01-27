package dto;

public class EducationStateDTO {
    private String education;

    public EducationStateDTO() {
    }

    public EducationStateDTO(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "EducationStateDTO{" +
                "education='" + education + '\'' +
                '}';
    }
}
