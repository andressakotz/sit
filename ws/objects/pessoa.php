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
			      $this->conn = $db;
			      	}

function read(){
   if(
    $this->name != '' &&
    $this->birthday != '' &&
    $this->email != '' &&
    $this->password != '')
    {
    $query = "SELECT * FROM usuario
        	WHERE email = ?";
    $stmt = $this->conn->prepare($query);
    $stmt->bind_param('s', $this->email);
    $stmt->execute();
    $stmt->store_result();
    
    return $stmt->num_rows();
    }else{
    	return 0;
    }
}
function login(){
    if(
        $this->email != '' &&
        $this->password != ''
    ){	
    $query = "SELECT id FROM usuario
              WHERE email = ? AND senha = ?";
    $stmt = $this->conn->prepare($query);
    $stmt->bind_param('ss', $this->email, $this->password);
    $stmt->execute();
    
    $result = $stmt->get_result();    
    $row = $result->fetch_array(MYSQLI_NUM);
    $this->userID = $row[0]; 
    return $row;
    }else{
    	return 0;
    }
}
function createlogin(){

	$query = "SELECT * FROM actualLogin
	               WHERE androidID = ?";
	  $stmt = $this->conn->prepare($query);
	  $stmt->bind_param('s', $this->androidID);
	  $stmt->execute();
	  $stmt->store_result();
	if($stmt->num_rows() < 1){
	    $query = "INSERT INTO actualLogin
		        (userID, androidID)
			    VALUES ('$this->userID', '$this->androidID')";

	        $stmt = $this->conn->query($query);
	    return true;
	}else{
	   return false;
	}
}      

		function create(){


			      if(
				              $this->name != '' &&
					              $this->birthday != '' &&
						              $this->email != '' &&
							              $this->password != ''
								          ){
										      $this->name=htmlspecialchars(strip_tags($this->name));
										          $this->birthday=htmlspecialchars(strip_tags($this->birthday));
										          $this->email=htmlspecialchars(strip_tags($this->email));
											      $this->password=htmlspecialchars(strip_tags($this->password));


											      $query = "INSERT INTO usuario
												          (nome, email, senha, sexo, localidade, bday)
													      VALUES ('$this->name', '$this->email', '$this->password', '$this->gender', '$this->place', '$this->birthday')";

    $stmt = $this->conn->query($query);
    return true;
    }else{
      return false;
    }
		}
function delete(){

			$query = "DELETE FROM actualLogin WHERE
						          userID = ?";

		$stmt = $this->conn->prepare($query);
		$stmt->bind_param('s', $this->userID);
		$stmt->execute();
		$result = $stmt->get_result();

				$query = "DELETE FROM usuario WHERE
							          id = ?";

		$stmt = $this->conn->prepare($query);
		$stmt->bind_param('s', $this->userID);
		$stmt->execute();
		$result = $stmt->get_result();

				return true;
			}
}

