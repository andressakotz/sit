<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';

include_once '../objects/feedback.php';

$database = new Database();
$db = $database->getConnection(); //cria conexao

if($db){

  $feedback = new feedback($db);

//vai pegar os dados inseridos pelo usuario na tela de feedback
  $feedback->assunto = $_POST['assunto'];
  $feedback->categoria = $_POST['categoria'];
  $feedback->descricao = $_POST['descricao'];
  $feedback->dateFeedback = $_POST['data'];

//vai tentar criar no bd
  if($feedback->create()){
      http_response_code(200);
      echo "true"; //se estiver ok retona 200 e true
  }

  else{
      http_response_code(400);
      echo '{';
          echo '"message": "Não foi possível enviar."';
      echo '}'; //falha na validação dos campos
  }
}else{
     http_response_code(400);
  echo '{';
      echo '"message": "Não foi possível acessar o banco."';
  echo '}';
}
?>
