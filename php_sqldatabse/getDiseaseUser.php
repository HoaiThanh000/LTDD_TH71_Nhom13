<?php
    include "connect.php";
    $query = "SELECT * FROM disease_user";
    $data = mysqli_query($conn, $query);
    $mangDiseaseUser = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangDiseaseUser, new diseaseUser(
            $row['UserID'],
            $row['DiseaseID'],
            $row['Saved']));
    }
    echo json_encode($mangDiseaseUser);
    class diseaseUser{
        function __construct($UserID, $DiseaseID, $Saved){
            $this->UserID = $UserID;
            $this->DiseaseID = $DiseaseID;
            $this->Saved = $Saved;
        }
    } 
?>