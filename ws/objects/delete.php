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

	  $pessoa = new pessoa($db);

	    $pessoa->email = $_POST['email'];
	    $pessoa->password = $_POST['password'];

	      if($pessoa->login()){
		          $pessoa->delete();
			      http_response_code(200);
			      echo "true";
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

