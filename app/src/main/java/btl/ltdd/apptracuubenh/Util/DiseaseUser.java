package btl.ltdd.apptracuubenh.Util;

import android.util.Log;

public class DiseaseUser {
    private int userID, diseaseID, saved;

    public DiseaseUser(int userID, int diseaseID, int saved) {
        this.userID = userID;
        this.diseaseID = diseaseID;
        this.saved = saved;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }

    public void Xuat(){
        Log.d("item", String.format("diseaseID: %d, userID: %d, saved: %d",getDiseaseID(), getUserID(), getSaved()) );
    }
}
