<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';

$database = new Database();
$db = $database->getConnection(); //cria conexao

if($db){


  $rua = $_POST['rua'];
  $bairro = $_POST['bairro'];
  $descricao = $_POST['descricao'];

  if(
      $rua != '' &&
      $bairro != '' &&
      $descricao != ''
  ){
       //query q insere no banco
      $query = "INSERT INTO cadastroParada
      (localizacao, bairro, descricao)
      VALUES ('$rua', '$bairro', '$descricao')";
      //retorna se o insert deu certo
      $stmt = $db->query($query);
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
