
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
	$imei = $_POST['imei'];
	//retrieve passcode
	$passcode = $_POST['passcode'];

	//make sure both variables have been set and are not empty
	if(isset($imei) && isset($passcode) && !empty($imei) && !empty($passcode)) {
		
		//opens connection to database
		$db_conn = connect();

		if(!$db_conn) { return false; }

		$sql = "SELECT mobile_banking FROM Customer";

		$uid = 0;

		$result = mysqli_query($db_conn, $sql);

		//Searches the returned mobile banking imeis and sets uid if a match is found
		while ($row = mysqli_fetch_assoc($result)) {
			if(strpos(trim(decrypt($row['mobile_banking'])), $imei) !== false) {
				$uid = ($i == 10) ? "1000000" : "10000000" . ($i+1);
				break;
			}
			$i++;
		}

		if($uid <= 0) {
			echo ERROR;
			return;
		}

		close($db_conn);

		//retrieve passcode data from database
		$data = fetch("Customer", "passcode", $uid, CUSTOMER);

		if(!$data) { return; }

		//Ensures the passcode entered by user and the users set passcode match
		if($data == $passcode) {
			$customer = new Customer();
			//set the user id of the object
			$customer->uid = $uid;
			//goes through every property and fetchs the neccesary result from db
			//reference $value to enable alteration
			foreach($customer as $key => &$value) {
				if($key != 'uid') {
					$value = fetch("Customer", $key, $customer->uid, CUSTOMER);
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
	else if(isset($imei) && isset($passcode) && empty($imei) && !empty($passcode)) {
		echo NEW_USER;
	}
	//something has gone wrong
	else {
		echo ERROR;
	}

?>