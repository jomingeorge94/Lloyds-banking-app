
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

	//retrieve uid
	$uid = $_POST['uid'];
	//retrieve passcode
	$passcode = $_POST['passcode'];

	//make sure both variables have been set
	if(isset($uid) && isset($passcode)) {
		//retrieve passcode data from database
		$data = fetch("customer", "passcode", decrypt($uid));

		//Ensures the passcode entered by user and the users set passcode match
		if( strcmp($passcode, $data['passcode']) == 0 ) {
			//Retrieve all data and put into a JSON object
			//Return successful JSON object to the android studio
			echo json_encode($customer);
		}
		else {
			//set json object valid to false
			$json = array("error" => true);
			echo json_encode($json);
		}
	}
?>