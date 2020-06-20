package btl.ltdd;

public class Disease {
    private int diseaseID;
    private int diseaseName;
    private int symptom;
    private int advice;
    private int Illustration;
    private int groupID;

    public Disease(int diseaseID, int diseaseName, int symptom, int advice, int illustration, int groupID) {
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
        this.symptom = symptom;
        this.advice = advice;
        Illustration = illustration;
        this.groupID = groupID;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public int getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(int diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getSymptom() {
        return symptom;
    }

    public void setSymptom(int symptom) {
        this.symptom = symptom;
    }

    public int getAdvice() {
        return advice;
    }

    public void setAdvice(int advice) {
        this.advice = advice;
    }

    public int getIllustration() {
        return Illustration;
    }

    public void setIllustration(int illustration) {
        Illustration = illustration;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
