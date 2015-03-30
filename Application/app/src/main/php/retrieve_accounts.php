<?php

	require('config_db.php');
	require('encryption.php');
	require('accounts.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {
		$accounts = array();
		$i = 0;
		//opens connection to database
		$db_conn = connect();

		if(!$db_conn) { return false; }

		//SQL query to fetch record
		$sql = "SELECT account_number,sortcode,type_of_account,total_money,available_money,overdraft" .
			   " FROM Accounts" .
			   " WHERE aid = '" . $uid . "'";

		$result = mysqli_query($db_conn, $sql);

		while ($row = mysqli_fetch_assoc($result)) {
			$a = new Accounts();

			foreach($a as $key => &$value) {
				if($key == 'aid') {
					//make aid = to the uid since it doesnt need to be decrypted
					$a->aid = $uid;
				}
				else {
					$value = trim(decrypt($row[$key]));
				}
			}

			$accounts[$i] = $a;
			$i++;
		}

		close($db_conn);

		//serializes json object into a string and output the result
		echo json_encode($accounts);
	}
	else {
		echo ERROR;
	}

?>