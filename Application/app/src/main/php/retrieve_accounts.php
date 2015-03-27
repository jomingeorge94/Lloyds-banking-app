<?php

	require('db.php');
	//require('config_db.php');
	require('accounts.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {
/*		$data = array();

		$sql = "SELECT COUNT(*) FROM Accounts";
		//opens connection to database
		$db_conn = connect();

		if(!$db_conn) { return false; }

		$result = mysqli_query($db_conn, $sql);

		$row = mysqli_fetch_array($result, MYSQLI_NUM);
*/
		$accounts = new Accounts();
		//set the user id of the object
		$accounts->aid = $uid;
		//goes through every property and fetchs the neccesary result from db
		//reference $value to enable alteration
		foreach($accounts as $key => &$value) {
			//ensures that uid is not fetched from the table
			if($key != 'aid') {
				$value = fetch("Accounts", $key, $uid, ACCOUNTS);
			}
		}

		//$data[$n] = json_encode($accounts);
		//serializes json object into a string and output the result
		echo json_encode($accounts);
	}
	else {
		echo ERROR;
	}

?>