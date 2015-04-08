<?php

	require('db.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {
		$array_of_data = fetch("Budget", "dateofbudget,amount,spend,travel_amount,beauty_and_hygiene_amount,entertainment_amount,other_amount,clothes_amount,leisurely_activities_amount,bills_amount,savings_amount,rent_amount,groceries_amount,eating_out_amount", $uid, BUDGET);

		echo json_encode($array_of_data);
	}
	else {
		echo ERROR;
	}


?>