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

  $pessoa = new pessoa($db); //a classe pessoa vai receber email, password e android id p tentar fazer login

  $pessoa->email = $_POST['email'];
  $pessoa->password = $_POST['password'];
  $pessoa->androidID = $_POST['androidID'];

//entra nesse if caso exista o cadastro
  if($pessoa->login()){
    if($pessoa->createlogin()){ //cria o login na tabela de login no bd
      http_response_code(200); //variavel de retorno php, onde 200 é ok
      echo "existe";
    }else{
      http_response_code(400); // //variavel retorno php, onde 400 é bad response
      echo '"Não encontrado"';
    }
  }
  else{
      http_response_code(400);
      echo '"Não encontrado"';
  }
}else{
     http_response_code(400); //caso n consiga acessar o banco
  echo '{';
      echo '"message": "Não foi possível acessar o banco."';
  echo '}';
}
?>
