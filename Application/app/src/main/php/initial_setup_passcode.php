<?php

	/**
	 *
	 * @file initial_setup_passcode.php
	 * @author Artemiy Bozhenok
	 * @date 25/03/2015
	 * @version 0.0.1
	 *
	 * sets the passcode and imei and also retrieves the necessary data
	 *
	 */

	require_once('db.php');
	require_once('customer.php');

	$uid = $_POST['uid'];
	$passcode = $_POST['passcode'];
	$imei = $_POST['imei'];

	if(isset($uid) && isset($passcode) && isset($imei)) {
		//set the passcode that the user has entered into the app
		modify("Customer", "passcode", $passcode, $uid);
		//set IMEI of the device to the mobile_banking attribute - indicate it is assigned to one device
		modify("Customer", "mobile_banking", $imei, $uid);
		//data
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
		echo ERROR;
	}

?>