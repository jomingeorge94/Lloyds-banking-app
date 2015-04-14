<?php

	require('config_db.php');
	require('encryption.php');
	require('transactions.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {

		$transactions = array();
		$i = 0;
		//opens connection to database
		$db_conn = connect();

		if(!$db_conn) { return false; }

		//SQL query to fetch record
		$sql = "SELECT to_from,amount,date" .
			   " FROM Transactions" .
			   " WHERE uid = '" . $uid . "'";

		$result = mysqli_query($db_conn, $sql);

		while ($row = mysqli_fetch_assoc($result)) {
			$a = new Transactions();

			foreach($a as $key => &$value) {
				$value = trim(decrypt($row[$key]));
			}

			$transactions[$i] = $a;
			$i++;
		}

		close($db_conn);

		if(empty($transactions)) {
			echo NO_TRANSACTIONS;
		}
		else {
			echo json_encode($transactions);
		}

	}
	else {
		echo ERROR;
	}


?>