<?php

	/**
	 *
	 * @file check_first_time_user.php
	 * @author Artemiy Bozhenok
	 * @date 23/03/2015
	 * @version 0.0.1
	 *
	 * Checks users inputs when trying to set up mobile banking
	 *
	 */

	require('db.php');
	//user entered online id
	$uid = $_POST['uid'];
	//user entered password
	$online_password = $_POST['online_password'];
	//imei
	$imei = $_POST['imei'];

	if(isset($uid) && isset($online_password)) {

		$mobile_banking = fetch("Customer", "online_password", $uid);

		//check if the passwords match
		if($online_password == $mobile_banking) {
			//set IMEI of the device to the mobile_banking attribute - indicate it is assigned to one device
			modify("Customer", "mobile_banking", $imei, $uid);
			//return success
			echo SUCCESS_FIRST_TIME;
		}
		else {
			echo ONLINE_PASSWORD_DO_NOT_MATCH;
		}
	}
	else {
		echo ERROR;
	}

?>