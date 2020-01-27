package tm;

public class EducationStatusTM {
    private String education;

    public EducationStatusTM() {
    }

    public EducationStatusTM(String education) {
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
        return "EducationStatus{" +
                "education='" + education + '\'' +
                '}';
    }
}
