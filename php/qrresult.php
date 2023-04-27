<?php 

    $con = mysqli_connect("localhost", "fnzmdpfak", "thdthdwls661!", "fnzmdpfak");
    mysqli_query($con,'SET NAMES utf8');

    $Cart_Product_ID = isset($_POST["Cart_Product_ID"]) ? $_POST["Cart_Product_ID"] : "";
    $Price = isset($_POST["Price"]) ? $_POST["Price"] : "";
    $Cart_Product_Name = isset($_POST["Cart_Product_Name"]) ? $_POST["Cart_Product_Name"] : "";

    $statement = mysqli_prepare($con, "INSERT INTO cart VALUES (?,?,?)");
    mysqli_stmt_bind_param($statement, "sis", $Cart_Product_ID, $Price, $Cart_Product_Name);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);


?>