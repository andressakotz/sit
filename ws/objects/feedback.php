<?php
class Feedback{
  private $conn;
	public $assunto;
  public $categoria;
	public $descricao;
	public $dataFeedback;


  public function __construct($db){ //função constroi o objeto feedback
    $this->conn = $db;
	}
//verifica se os dados são validos
  function create(){
    if(
        $this->assunto != '' &&
        $this->categoria != '' &&
        $this->descricao != '' &&
        $this->dataFeedback != ''
    ){
//query q insere no banco
    $query = "INSERT INTO feedback
    (assunto, categoria, descricao, dataFeedback)
    VALUES ('$this->assunto', '$this->categoria', '$this->descricao', '$this->dataFeedback')";
//retorna se o insert deu certo
    $stmt = $this->conn->query($query);
    return true;
    }else{
      return false;
    }
  }
}
?>
