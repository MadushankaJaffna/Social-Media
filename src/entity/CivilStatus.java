package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CivilStatus implements SuperEntity {
    @Id
    private String civilStatus;

    public CivilStatus() {
    }

    public CivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    @Override
    public String toString() {
        return "CivilStatus{" +
                "civilStatus='" + civilStatus + '\'' +
                '}';
    }
}
