package prog;

public class JournalEntry {
    String date;
    String Entry;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntry() {
        return Entry;
    }

    public void setEntry(String entry) {
        Entry = entry;
    }

    public JournalEntry(String date, String entry) {
        this.date = date;
        Entry = entry;
    }

    @Override
    public String toString() {
        return getDate() + "\n" + getEntry();
    }
}
