<?php
    include "connect.php";
    $query = "SELECT * FROM user";
    $data = mysqli_query($conn, $query);
    $mangUser = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangUser, new user(
            $row['UserID'],
            $row['FullName'],
            $row['UserName'],
            $row['Password'],
            $row['Phone'],
            $row['Job'],
            $row['TypeUser']));
    }
    echo json_encode($mangUser);
    class user{
        function __construct($UserID, $FullName, $UserName, $Password, $Phone, $Job, $TypeUser){
            $this->UserID = $UserID;
            $this->FullName = $FullName;
            $this->UserName = $UserName;
            $this->Password = $Password;
            $this->Phone = $Phone;
            $this->Job = $Job;
            $this->TypeUser = $TypeUser;
        }
    }
?>