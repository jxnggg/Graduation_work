<?


$con = mysqli_connect("localhost", "fnzmdpfak", "thdthdwls661!", "fnzmdpfak");
    mysqli_query($con,'SET NAMES utf8');

    
$result = mysqli_query($con, "SELECT * FROM cart;");


$response = array();//배열 선언



while($row = mysqli_fetch_array($result)){

//response["userID"]=$row[0] ....이런식으로 됨.

array_push($response, array("Cart_Product_ID"=>$row[0], "Price"=>$row[1], "Cart_Product_Name"=>$row[2] ));

}



//response라는 변수명으로 JSON 타입으로 $response 내용을 출력


    
   echo json_encode(array("response"=>$response));
    // DB 연동을 종료 합니다
    mysqli_close($con);
        
    

    

?>