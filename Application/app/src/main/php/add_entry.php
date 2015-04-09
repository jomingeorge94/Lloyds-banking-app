<?php

	require('db.php');
	require('error_lib.php');

	$uid = $_POST['uid'];
	$purchase = $_POST['purchase'];
	$category = $_POST['category'];

	if(isset($purchase) && isset($category)) {

		switch($category) {
			case "Food":
				$current_purchase = fetch("Budget", "groceries_spend", $uid, BUDGET);
				modify("Budget", "groceries_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
			case "Travel":
				$current_purchase = fetch("Budget", "travel_spend", $uid, BUDGET);
				modify("Budget", "travel_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
			case "Beauty":
				$current_purchase = fetch("Budget", "beauty_and_hygiene_spend", $uid, BUDGET);
				modify("Budget", "beauty_and_hygiene_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
			case "Entertainment":
				$current_purchase = fetch("Budget", "entertainment_spend", $uid, BUDGET);
				modify("Budget", "entertainment_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
			case "Home":
				$current_purchase = fetch("Budget", "home_spend", $uid, BUDGET);
				modify("Budget", "home_spend", $purchase, $current_purchase + $uid, BUDGET);
				break;
			case "Clothes":
				$current_purchase = fetch("Budget", "clothes_spend", $uid, BUDGET);
				modify("Budget", "clothes_spend", $purchase, $current_purchase + $uid, BUDGET);
				break;
			case "Leisure":
				$current_purchase = fetch("Budget", "leisurely_activities_spend", $uid, BUDGET);
				modify("Budget", "leisurely_activities_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
			case "Other":
				$current_purchase = fetch("Budget", "other_spend", $uid, BUDGET);
				modify("Budget", "other_spend", $current_purchase + $purchase, $uid, BUDGET);
				break;
		}

		echo ADD_ENTRY_COMPLETE;

	}
	else {
		echo ERROR;
	}


?>