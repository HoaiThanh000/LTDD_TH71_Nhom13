<?php
    include "connect.php";
    $userID = $_POST['userID'];
    $diseaseID = $_POST['diseaseID'];
    $query = "DELETE FROM disease_user
              WHERE `UserID` = '$userID' AND `DiseaseID` = '$diseaseID'";
    if (mysqli_query($conn, $query)){
        echo "Thành công";
    } else {
        echo "Thất bại";
    }
?>