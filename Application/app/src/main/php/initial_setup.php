<?php

	/**
	 *
	 * @file check_first_time_user.php
	 * @author Artemiy Bozhenok
	 * @date 23/03/2015
	 *
	 * Checks users inputs when trying to set up mobile banking
	 *
	 */

	require('db.php');
	//user entered online id
	$uid = $_POST['uid'];
	//user entered password
	$online_password = $_POST['online_password'];

	if(isset($uid) && isset($online_password)) {

		$mobile_banking = fetch("Customer", "online_password", $uid, CUSTOMER);

		//check if the passwords match
		if($online_password == $mobile_banking) {
			//return success
			echo SUCCESS_FIRST_TIME;
		}
		else {
			//passwords did not match error
			echo ONLINE_PASSWORD_DO_NOT_MATCH;
		}
	}
	else {
		echo ERROR;
	}

?>