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
           
         
	    $pessoa->name = $_POST['name'];
	    $pessoa->birthday = $_POST['birthday'];
	      $pessoa->email = $_POST['email'];
	      $pessoa->password = $_POST['password'];
	      $pessoa->gender = $_POST['gender'];
	      $pessoa->place = $_POST['place'];
	      $r = $pessoa->read();
	      
	        if($r){
			    http_response_code(200);
			        
			            echo 'existe';
			        
			      }elseif($pessoa->create()){
				            http_response_code(200);
					          
					              echo 'true';
					          
					      }

		  else{
			        http_response_code(400);
				      echo '{';
				          echo '"message": "Não foi possível cadastrar."';
				      echo '}';
				  }
}else{
	     http_response_code(400);
	       echo '{';
	           echo '"message": "Não foi possível acessar o banco."';
	       echo '}';
}
?>

