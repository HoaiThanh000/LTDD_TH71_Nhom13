<?php
    include "connect.php";
    $query = "SELECT * FROM chapter";
    $data = mysqli_query($conn, $query);
    $mangChuongBenh = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangChuongBenh, new chapterDisease(
            $row['ChapterID'],
            $row['ChapterName']));
    }
    echo json_encode($mangChuongBenh);
    class chapterDisease{
        function __construct($ChapterID, $ChapterName){
            $this->ChapterID = $ChapterID;
            $this->ChapterName = $ChapterName;
        }
    }
?>