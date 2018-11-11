<?php
class Pessoa{
	private $conn;
	public $name;
	public $birthday;
	public $email;
	public $password;
	public $gender;
	public $place;
	public function __construct($db){
		$this->conn = $db; //coloca a conexão na variavel conn
	}
//le e retona o numero de linhas encontradas
	function read(){
//if valida se os dados estão != 0
		if(
		$this->name != '' &&
		$this->birthday != '' &&
		$this->email != '' &&
		$this->password != '')
		{
//da um select no email pra encontrar o usuario
			$query = "SELECT * FROM usuario
			WHERE email = ?";
			$stmt = $this->conn->prepare($query);
			$stmt->bind_param('s', $this->email); //troca o ? pelo email encontrado
			$stmt->execute();
			$stmt->store_result();//coloca o result no stmt

			return $stmt->num_rows(); //stmt retorna o numero de linhas
		}else{
		return 0;
		}
	}
	function login(){
//valida o email e passw
	if(
		$this->email != '' &&
		$this->password != ''
		){
//da um select na senha e email iguais aos do banco
			$query = "SELECT id FROM usuario
			WHERE email = ? AND senha = ?";
			$stmt = $this->conn->prepare($query);
			$stmt->bind_param('ss', $this->email, $this->password);
			$stmt->execute();
			$result = $stmt->get_result();
			$row = $result->fetch_array(MYSQLI_NUM);
			$this->userID = $row[0];
			return $row; //se igual vai retornar a linha
		}else{
			return 0; //se n, retorna 0, dados invalidos ou user n cadastrado
		}
	}
	function createlogin(){ //verifica na tabela actuallogin se ja tem um androidid igual
		$query = "SELECT * FROM actualLogin
			               WHERE androidID = ?";
	  $stmt = $this->conn->prepare($query); //prepara a query p enviar p banco
	  $stmt->bind_param('s', $this->androidID); //troca o ? pela id do android
		$stmt->execute(); // executa p mandar pro banco
		$stmt->store_result(); // coloca na variável stmt o retorno do banco

//se n existe, ele cria a linha na tabela actuallogin com userid e androidid
		if($stmt->num_rows() < 1){
			$query = "INSERT INTO actualLogin
			(userID, androidID)
			VALUES ('$this->userID', '$this->androidID')";

			$stmt = $this->conn->query($query);
			return true;
    }else{
			return false; //é pq o dispositivo ja ta conectado
    }
	}

	function delete(){

		$query = "DELETE FROM actualLogin WHERE
		          userID = ?";

		$stmt = $this->conn->prepare($query);
		$stmt->bind_param('s', $this->userID); //vai substituir o ? pelo user
		$stmt->execute();
		$result = $stmt->get_result();

//deleta o usuario
		$query = "DELETE FROM usuario WHERE
		          id = ?";

		$stmt = $this->conn->prepare($query);
		$stmt->bind_param('s', $this->userID);
		$stmt->execute();
		$result = $stmt->get_result();

		return true;
	}

	function create(){
//valida os dados
		if(
		$this->name != '' &&
		$this->birthday != '' &&
		$this->email != '' &&
		$this->password != ''
		){
//reconhece caractere especial
		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->birthday=htmlspecialchars(strip_tags($this->birthday));
		$this->email=htmlspecialchars(strip_tags($this->email));
		$this->password=htmlspecialchars(strip_tags($this->password));

//da um insert no banco com os dados inserido pelo usuario
		$query = "INSERT INTO usuario
		(nome, email, senha, sexo, localidade, bday)
		VALUES ('$this->name', '$this->email', '$this->password', '$this->gender', '$this->place', '$this->birthday')";

		$stmt = $this->conn->query($query); //envia a query pro banco
		return true;
		}else{
		return false; //retorna caso tenha dado invalido
		}
	}
}
