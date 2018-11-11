<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';

include_once '../objects/pessoa.php';

$database = new Database();
$db = $database->getConnection();

if($db){

	$androidID = $_POST['androidID'];
	$query = "SELECT * FROM actualLogin
		               WHERE androidID = ?";
    	$stmt = $db->prepare($query);
    	$stmt->bind_param('s', $androidID);
	$stmt->execute();
	$result = $stmt->get_result();    
	$row = $result->fetch_array(MYSQLI_NUM);
	 
	if($row){
	  $userID = $row[1];	
	  $query = "SELECT * FROM usuario
		                WHERE id = ?";
    	  $stmt = $db->prepare($query);
          $stmt->bind_param('s', $userID);
          $stmt->execute();
          $result = $stmt->get_result();  
	  $row = $result->fetch_array(MYSQLI_NUM);
	  if($row){
             http_response_code(200);
             echo json_encode($row);
	     //echo '"true"'; 
	  }else{
	     http_response_code(400);
	     echo '"Não encontrado"';
	  } 
	  
	}
	else{
	  http_response_code(400);
	  echo '"Não encontrado"';

	}
}else{
	     http_response_code(400);
	       echo '{';
	           echo '"message": "Não foi possível acessar o banco."';
	       echo '}';
}
?>

