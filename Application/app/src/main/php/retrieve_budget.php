<?php

	require('db.php');

	$uid = $_POST['uid'];

	if(isset($uid)) {
		$array_of_data = fetch("Budget", "dateofbudget,amount,spend,travel_amount,beauty_and_hygiene_amount,entertainment_amount,home_amount,other_amount,clothes_amount,leisurely_activities_amount,bills_amount,savings_amount,rent_amount,groceries_amount,eating_out_amount,travel_spend,beauty_and_hygiene_spend,entertainment_spend,home_spend,other_spend,clothes_spend,leisurely_activities_spend,bills_spend,savings_spend,rent_spend,groceries_spend,eating_out_spend,monday_spend,tuesday_spend,wednesday_spend,thursday_spend,friday_spend,saturday_spend,sunday_spend", $uid, BUDGET);

		echo json_encode($array_of_data);
	}
	else {
		echo ERROR;
	}


?>