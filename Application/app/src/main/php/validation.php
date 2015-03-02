
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

	//retrieve uid
	$uid = $_POST['uid'];
	//retrieve passcode
	$passcode = $_POST['passcode'];

	//make sure both variables have been set and are not empty
	if(isset($uid) && isset($passcode) && !empty($uid) && !empty($passcode)) {
		//decrypt the uid stored in the SQLite
		//$uid = decrypt($uid);
		//retrieve passcode data from database
		$data = fetch("customer", "passcode", $uid);

		if(!$data) { return; }

		//Ensures the passcode entered by user and the users set passcode match
		if($data == $passcode) {
			$customer = new Customer();
			//set the user id of the object
			$customer->uid = $uid;
			//goes through every property and fetchs the neccesary result from db
			//reference $value to enable alteration
			foreach($customer as $key => &$value) {
				//ensures that uid is not fetched from the table
				if($key != 'uid') {
					$value = fetch("customer", $key, $uid);
				}
			}

			//serializes json object into a string and output the result
			echo json_encode($customer);
		}
		else {
			//outputs false which can then get retrieved by AS
			echo WRONG_PASSCODE;
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