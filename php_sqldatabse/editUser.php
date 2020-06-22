<?php
    include "connect.php";
    $fullName = $_POST['fullName'];
    $phone = $_POST['phone'];
    $job = $_POST['job'];
    $userID = $_POST['userID'];
    $query = "UPDATE user 
              SET `FullName` = '$fullName', `Phone` = '$phone', `Job` = '$job'
              WHERE `UserID` = '$userID'";
    if (mysqli_query($conn, $query)){
        echo "Thành công";
    } else {
        echo "Thất bại";
    }
?>