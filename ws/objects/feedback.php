<?php
class Feedback{
	  private $conn;
	  public $assunto;
	    public $categoria;
	  public $descricao;
	  public $dataFeedback;


	    public function __construct($db){
		        $this->conn = $db;
	    }

	    function create(){
		        if(
				        $this->assunto != '' &&
					        $this->categoria != '' &&
						        $this->descricao != '' &&
							        $this->dataFeedback != ''
								    ){

									        $query = "INSERT INTO feedback
											    (assunto, categoria, descricao, dataFeedback)
											        VALUES ('$this->assunto', '$this->categoria', '$this->descricao', '$this->dataFeedback')";

    $stmt = $this->conn->query($query);
    return true;
    }else{
      return false;
    }
  }
}
?>

