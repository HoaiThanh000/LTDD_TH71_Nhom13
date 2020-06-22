<?php
    include "connect.php";
    $search = $_POST['diseaseID'];
    $query = "SELECT * FROM disease WHERE `DiseaseID` = '$search'";
    $data = mysqli_query($conn, $query);
    $mangBenh = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangBenh, new disease(
            $row['DiseaseID'],
            $row['DiseaseName'],
            $row['Symptom'],
            $row['Advice'],
            $row['Illustration'],
            $row['GroupID']));
    }
    echo json_encode($mangBenh);
    class disease{
        function __construct($DiseaseID, $DiseaseName, $Symptom, $Advice, $Illustration, $GroupID){
            $this->DiseaseID = $DiseaseID;
            $this->DiseaseName = $DiseaseName;
            $this->Symptom = $Symptom;
            $this->Advice = $Advice;
            $this->Illustration = $Illustration;
            $this->GroupID = $GroupID;
        }
    }
?>