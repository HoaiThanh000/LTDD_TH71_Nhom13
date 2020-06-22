<?php
    include "connect.php";
    $userID = $_POST['userID'];
    $diseaseID = $_POST['diseaseID'];
    $saved = $_POST['saved'];
    $query = "UPDATE disease_user 
              SET `Saved` = '$saved'
              WHERE `DiseaseID` = '$diseaseID' and `UserID` = '$userID'";
    if (mysqli_query($conn, $query)){
        echo "Thành công";
    } else {
        echo "Thất bại";
    }
?>