<?php 
    $con = mysqli_connect("localhost", "fnzmdpfak", "thdthdwls661!", "fnzmdpfak");
    mysqli_query($con,'SET NAMES utf8');

    $userID = isset($_POST["userID"]) ? $_POST["userID"] : "";
    $userPassword = isset($_POST["userPassword"]) ? $_POST["userPassword"] : "";
    $userName = isset($_POST["userName"]) ? $_POST["userName"] : "";
    $userAge = isset($_POST["userAge"]) ? $_POST["userAge"] : "";

    $statement = mysqli_prepare($con, "INSERT INTO user VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssi", $userID, $userPassword, $userName, $userAge);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>