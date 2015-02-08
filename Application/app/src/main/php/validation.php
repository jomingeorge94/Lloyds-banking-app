
<?php
	/**
	 *
	 * @file validation.php
	 * @author Artemiy Bozhenok
	 * @date 02/02/2015
	 * @version 0.0.1
	 *
	 * Passcode validation upon login
	 *
	 */

	require('db.php');
	require('customer.php');

	define('WRONG_PASSCODE', '1');
	define('NEW_USER', '2');
	define('ERROR', '3');

	//retrieve uid
	$uid = $_POST['uid'];
	//retrieve passcode
	$passcode = $_POST['passcode'];

	//make sure both variables have been set and are not empty
	if(isset($uid) && isset($passcode) && !empty($uid) && !empty($passcode)) {
		//decrypt the uid stored in the SQLite
		//$uid = decrypt($uid);958343
		//retrieve passcode data from database
		$data = fetch("test_table", "sortcode", $uid);

		echo '<p>' . strcmp($data, $passcode). '</p>';
		echo '<p>' . strcmp($passcode, $data) . '</p>';
		//Ensures the passcode entered by user and the users set passcode match
		if( strcmp($data, $passcode) == 0 ) {
			$customer = new Customer();

			//goes through every property and fetchs the neccesary result from db
			foreach($customer as $key => $value) {
				$value = fetch("test_table", $key, $uid);
			}

			//serializes json object into a string and output the result
			echo json_encode($customer);
		}
		else {
			//outputs false which can then get retrieved by AS
			//echo WRONG_PASSCODE;
		}
	}
	//uid empty = new user (not set up mobile banking)
	else if(isset($uid) && isset($passcode) && empty($uid) && !empty($passcode)) {
		echo NEW_USER;
	}
	//something has gone wrong
	else {
		echo ERROR;
	}

?>