<?php

	/**
	 *
	 * @file check_mobile_banking.php
	 * @author Artemiy Bozhenok
	 * @date 23/03/2015
	 * @version 0.0.1
	 *
	 * Tries to match the devices IMEI to determine if mobile
	 * banking has been set up on the device or not
	 *
	 */

	require('config_db.php');
	require('encryption.php');

	$imei = $_POST['imei'];

	if(isset($imei)) {

		$db_conn = connect();

		if(!$db_conn) { return false; }

		//SQL query to fetch record
		$sql = "SELECT " . "mobile_banking" . 
				" FROM " . "Customer"; 

		$result = mysqli_query($db_conn, $sql);

		$row = mysqli_fetch_array($result, MYSQLI_NUM);

		//if nothing found then indicate that user hasnt set up mobile banking
		if($result) {
			for($i = 0; $i < sizeof($row); $i++) {
				if(!is_null($row[$i])) {
					if(strpos(decrypt($row[$i]), $imei) !== false) {
						//go to login screen
						echo SUCCESS_MOBILE_BANKING;
						return;
					}
				}
			}
			//direct user to set up mobile banking
			echo NEW_USER;
		}
		else {
			echo ERROR;
		}

	}
	else {
		echo ERROR;
	}

?>