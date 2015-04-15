<?php

	require('error_lib.php');
	require('db.php');

	$uid = $_POST['uid'];
	//account number
	$from = $_POST['from'];
	$from_money = $_POST['from_money'];
	//account number
	$to = $_POST['to'];
	$to_money = $_POST['to_money'];
	//transfer amount
	$amount = $_POST['amount'];

	if(isset($uid) && isset($from) && isset($to) && isset($from_money) && isset($to_money) && isset($amount)) {

		$f_row = 0;
		$t_row = 0;
		//opens connection to database
		$db_conn = connect();

		if(!$db_conn) { return false; }

		//SQL query to fetch record
		$sql = "SELECT account_number, row" .
			   " FROM Accounts" .
			   " WHERE aid = '" . $uid . "'";

		$result = mysqli_query($db_conn, $sql);

		while ($row = mysqli_fetch_assoc($result)) {
			if(intval($from) == intval(trim(decrypt($row['account_number'])))) {
				$f_row = $row['row'];
			}
			else if(intval($to) == intval(trim(decrypt($row['account_number'])))) {
				$t_row = $row['row'];
			}
		}

		close($db_conn);

		//take away and update - account transfering from
		modify("Accounts", "total_money", trim((string) (floatval($from_money) - floatval($amount))), $f_row, ACCOUNTS);
		//add and update - account transfering to
		modify("Accounts", "total_money", trim((string) (floatval($to_money) + floatval($amount))), $t_row, ACCOUNTS);

		echo TRANSFER_SUCCESSFUL;
	}
	else {
		echo ERROR;
	}

?>