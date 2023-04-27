<?

$con = mysqli_connect("localhost", "fnzmdpfak", "thdthdwls661!", "fnzmdpfak");
    mysqli_query($con,'SET NAMES utf8');


    $statement = mysqli_prepare($con, "SELECT sum(Price) as sum_Price FROM cart");
    
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);

mysqli_stmt_bind_result($statement,$sum_Price);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)) 
    {
        $response["success"] = true;
        $response["sum_Price"] = $sum_Price;
    }

    echo json_encode($response);


?>


