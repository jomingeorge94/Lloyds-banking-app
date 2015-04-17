<?php

	/**
	 *
	 * @file changepasscode.php
	 * @author Artemiy Bozhenok
	 * @date 17/04/2015
	 *
	 * Changes the passcode
	 *
	 */

	require('db.php');

	//retrieve the data sent by POST
	$uid = $_POST['uid'];
	$passcode = $_POST['passcode'];
	//check if the data has been set
	if(isset($uid) && isset($passcode)) {
		//changed the passcode
		modify("Customer", "passcode", $passcode, $uid, CUSTOMER);
		//notify the passcode has been changed
		echo PASSCODE_CHANGED;
	}
	//this error should never happen
	else {
		echo ERROR;
	}