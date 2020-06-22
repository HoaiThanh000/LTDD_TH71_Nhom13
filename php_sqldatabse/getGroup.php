<?php
    include "connect.php";
    $query = "SELECT * FROM `group` WHERE 1";
    $data = mysqli_query($conn, $query);
    $mangNhomBenh = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangNhomBenh, new groupDisease(
            $row['GroupID'],
            $row['GroupName'],
            $row['ChapterID']));
    }
    echo json_encode($mangNhomBenh);
    class groupDisease{
        function __construct($GroupID, $GroupName, $ChapterID){
            $this->GroupID = $GroupID;
            $this->GroupName = $GroupName;
            $this->ChapterID = $ChapterID;
        }
    }
?>