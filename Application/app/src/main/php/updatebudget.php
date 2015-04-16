<?php

	require('db.php');

	$uid = $_POST['uid'];

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