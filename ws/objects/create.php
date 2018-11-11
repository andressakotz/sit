<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';

include_once '../objects/pessoa.php';

$database = new Database();
$db = $database->getConnection(); //cria conexão no bd

//se conectou
if($db){

  $pessoa = new pessoa($db); //cria o objeto pessoa pra criar os dados

//ŧela de cadastro, pega o q o usuario insere
  $pessoa->name = $_POST['name'];
  $pessoa->birthday = $_POST['birthday'];
  $pessoa->email = $_POST['email'];
  $pessoa->password = $_POST['password'];
  $pessoa->gender = $_POST['gender'];
  $pessoa->place = $_POST['place'];
  $existe = $pessoa->read(); //valida se já os dados já existem


  if($existe){ //caso exista, retorna q já tem um cadastro igual no bd
    http_response_code(200);
    echo "existe";

  }elseif($pessoa->create()){ //se n existir, tenta criar no bd
      http_response_code(200);
      echo "true"; //caso deixe criar, ele retona true e 200 do php

  }

  else{
      http_response_code(400);
      echo '{';
          echo '"message": "Não foi possível cadastrar."';
      echo '}'; //algum problema nos campos do cadastro
  }
}else{
     http_response_code(400);
  echo '{';
      echo '"message": "Não foi possível acessar o banco."';
  echo '}';
}
?>
