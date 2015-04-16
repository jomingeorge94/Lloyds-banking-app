<?php

	/**
	 *
	 * @file updatebudget.php
	 * @author Artemiy Bozhenok
	 * @date 16/04/2015
	 *
	 * A script which is used to update the budget
	 *
	 */

	require('db.php');
	//retrieves the uid
	$uid = $_POST['uid'];
	//stores various data that is sent into an array
	$values = array(
		$_POST['amount'],
		$_POST['food'],
		$_POST['travel'],
		$_POST['beauty'],
		$_POST['entertainment'],
		$_POST['home'],
		$_POST['clothes'],
		$_POST['leisure'],
		$_POST['other']
	);
	//name of the attributes in the table
	$key = array(
		"amount",
		"groceries_amount",
		"travel_amount",
		"beauty_and_hygiene_amount",
		"entertainment_amount",
		"home_amount",
		"clothes_amount",
		"leisurely_activities_amount",
		"other_amount"
	);
	//checks if the global variables have been set
	if(isset($uid) && isset($values) && isset($key)) {
		//modify every budget category
		for($i = 0; $i < sizeof($values); $i++) {
			modify("Budget", $key[$i], $values[$i], $uid, BUDGET);
		}
		//returns the what the values were updated to
		echo BUDGET_FINISHED;

	}
	else {
		echo ERROR;
	}

?>