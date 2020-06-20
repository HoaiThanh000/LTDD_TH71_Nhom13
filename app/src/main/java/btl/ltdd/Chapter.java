package btl.ltdd;

public class Chapter {
    int chapterID;
    String chapterName;

    public Chapter(int chapterID, String chapterName) {
        this.chapterID = chapterID;
        this.chapterName = chapterName;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
