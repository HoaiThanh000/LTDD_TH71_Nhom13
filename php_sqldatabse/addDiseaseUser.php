<?php
    include "connect.php";
    $userID = $_POST['userID'];
    $diseaseID = $_POST['diseaseID'];
    $query = "INSERT INTO disease_user(UserID, DiseaseID, Saved)
                VALUES ('$userID', '$diseaseID', 0)";
    if (mysqli_query($conn, $query)){
        echo "Thành công";
    } else {
        echo "Thất bại";
    }
?>