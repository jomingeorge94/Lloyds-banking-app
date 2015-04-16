<?php

	/**
	 *
	 * @file maketransfer.php
	 * @author Artemiy Bozhenok
	 * @date 15/04/2015
	 *
	 * This will retrieve the account number and rows of the user uid. Then matches the row number with the account
	 * number provided from the java (the selected accounts) and transfers the money between them
	 *
	 */

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
		//used to identify which row to update
		$f_row = 0;
		$t_row = 0;
		//opens connection to database
		$db_conn = connect();
		//has the connection been established
		if(!$db_conn) { return false; }

		//SQL query to fetch record
		$sql = "SELECT account_number, row" .
			   " FROM Accounts" .
			   " WHERE aid = '" . $uid . "'";

		$result = mysqli_query($db_conn, $sql);
		//iterates throught the results
		while ($row = mysqli_fetch_assoc($result)) {
			//checks if the results account number matches the given account number and then updates the row number
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
		//indicate that the transfer was successful
		echo TRANSFER_SUCCESSFUL;
	}
	else {
		echo ERROR;
	}

?>