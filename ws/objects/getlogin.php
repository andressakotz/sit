<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';

include_once '../objects/pessoa.php';

$database = new Database();
$db = $database->getConnection(); //cria conexao

if($db){

	$androidID = $_POST['androidID']; //o android envia o androidid do user
//a query procura o androidid na tabela actuallogin
	$query = "SELECT * FROM actualLogin
		               WHERE androidID = ?";
  $stmt = $db->prepare($query);
  $stmt->bind_param('s', $androidID);
	$stmt->execute();
	$result = $stmt->get_result();
	$row = $result->fetch_array(MYSQLI_NUM); //pega a linha q tenha o id

//se a linha existe, procura o user id na tabela de usuario
	if($row){
	  $userID = $row[1];
	  $query = "SELECT * FROM usuario
		                WHERE id = ?";
	  $stmt = $db->prepare($query);
    $stmt->bind_param('s', $userID);
    $stmt->execute();
    $result = $stmt->get_result();
	  $row = $result->fetch_array(MYSQLI_NUM);
//se encontra o user id, envia a linha em json pro android
	  if($row){
       http_response_code(200);
       echo json_encode($row); //encoda o json p android entender
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
