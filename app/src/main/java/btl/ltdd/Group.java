package btl.ltdd;

public class Group {
    private int groupID;
    private String groupName;
    private int chapterID;

    public Group(int groupID, String groupName, int chapterID) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.chapterID = chapterID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }
}
