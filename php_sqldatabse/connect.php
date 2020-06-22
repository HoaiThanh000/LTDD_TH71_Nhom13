<?php
    $host = "fdb24.awardspace.net";
    $username = "3474882_dbdisease";
    $password = "Qwer1234!@#";
    $database = "3474882_dbdisease";

    $conn = mysqli_connect($host, $username, $password, $database);
    mysqli_query($conn, "SET NAMES 'UTF8'");
?>