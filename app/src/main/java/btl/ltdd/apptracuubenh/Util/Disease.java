package btl.ltdd.apptracuubenh.Util;

import android.util.Log;

public class Disease {
    private int diseaseID;
    private String diseaseName;
    private String symptom;
    private String advice;
    private String Illustration;
    private int groupID;

    public Disease(int diseaseID, String diseaseName, String symptom, String advice, String illustration, int groupID) {
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
        this.symptom = symptom;
        this.advice = advice;
        Illustration = illustration;
        this.groupID = groupID;
    }

    public Disease(String diseaseName, String symptom) {
        this.diseaseName = diseaseName;
        this.symptom = symptom;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getIllustration() {
        return Illustration;
    }

    public void setIllustration(String illustration) {
        Illustration = illustration;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

}
