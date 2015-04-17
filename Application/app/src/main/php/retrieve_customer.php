<?php

	/**
	 *
	 * @file retrieve_customer.php
	 * @author Artemiy Bozhenok
	 * @date 17/04/2015
	 *
	 * Retrieves the customer data
	 *
	 */

	require('db.php');
	require('customer.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {
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