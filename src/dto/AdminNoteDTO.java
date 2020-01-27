package dto;

import entity.SuperEntity;

public class AdminNoteDTO implements SuperEntity {
    private String note;

    public AdminNoteDTO() {
    }

    public AdminNoteDTO(String note) {
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
