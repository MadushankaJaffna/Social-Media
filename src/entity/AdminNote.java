package entity;

import org.springframework.context.annotation.ImportResource;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminNote implements SuperEntity {
    @Id
    private String note;

    public AdminNote() {
    }

    public AdminNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "AdminNote{" +
                "note='" + note + '\'' +
                '}';
    }
}
