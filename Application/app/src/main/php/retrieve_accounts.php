<?php

	/**
	 *
	 * @file retrieve_accounts.php
	 * @author Artemiy Bozhenok
	 * @date 13/04/2015
	 *
	 * Retrieves all the users accounts from the database
	 */

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
		//go through the multiple results
		while ($row = mysqli_fetch_assoc($result)) {
			$a = new Accounts();
			//go through the class and update the variables in the class
			foreach($a as $key => &$value) {
				if($key == 'aid') {
					//make aid = to the uid since it doesnt need to be decrypted
					$a->aid = $uid;
				}
				else {
					$value = trim(decrypt($row[$key]));
				}
			}
			//add the filled class to the array
			$accounts[$i] = $a;
			//increment counter
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