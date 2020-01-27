package tm;

public class AdminNoteTM {
    private String note;

    public AdminNoteTM() {
    }

    public AdminNoteTM(String note) {
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
        return "AdminNoteTM{" +
                "note='" + note + '\'' +
                '}';
    }
}
