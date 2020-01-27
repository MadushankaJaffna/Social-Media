package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EducationState implements SuperEntity {
    @Id
    private String education;

    public EducationState() {
    }

    public EducationState(String education) {
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
        return "EducationState{" +
                "education='" + education + '\'' +
                '}';
    }
}
