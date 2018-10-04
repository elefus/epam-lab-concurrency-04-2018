package homework.part2;

public class Storage {
    private String string;
    public volatile boolean readerIsHere;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
