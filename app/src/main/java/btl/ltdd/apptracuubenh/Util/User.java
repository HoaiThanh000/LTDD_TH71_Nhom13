package btl.ltdd.apptracuubenh.Util;

public class User {
    private int userID, type;
    private String fullName, userName, password, phone, job;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int userID, String fullName, String userName, String password, String phone, String job) {
        this.userID = userID;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.job = job;
    }

    public User(int userID, int type, String fullName, String userName, String password, String phone, String job) {
        this.userID = userID;
        this.type = type;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.job = job;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
