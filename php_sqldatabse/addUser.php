<?php
    include "connect.php";
    $userName = $_POST['userName'];
    $passWord = $_POST['passWord'];
    $query = "INSERT INTO user(UserID, FullName, UserName, Password, Phone, Job, TypeUser)
                VALUES (null, '', '$userName', '$passWord', '', '', 0)";
    if (mysqli_query($conn, $query)){
        $UserID = $conn->insert_id;
        echo $UserID;
    } else {
        echo "Thất bại";
    }
?>