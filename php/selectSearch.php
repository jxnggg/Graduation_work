<?


$con = mysqli_connect("localhost", "fnzmdpfak", "thdthdwls661!", "fnzmdpfak");
    mysqli_query($con,'SET NAMES utf8');

    $Product_name=$_POST["Product_name"];

    $statement = mysqli_prepare($con, "SELECT Product_name,Price,Product_info,Location,Size,Volume FROM product,product_detail,category WHERE Product_ID = Detail_Product_ID AND Product_ID =Category_Product_ID AND Product_name = ? ");

    mysqli_stmt_bind_param($statement, "s", $Product_name);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$Product_name, $Price,$Product_info,$Location,$Size,$Volume);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["Product_name"] = $Product_name;
        $response["Price"] = $Price;
        $response["Product_info"] = $Product_info;
        $response["Location"] = $Location;
        $response["Size"] = $Size;
        $response["Volume"] = $Volume;
        
    }

    echo json_encode($response);

?>


