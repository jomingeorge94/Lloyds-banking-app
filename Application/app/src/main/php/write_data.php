
<?php

	require('db.php');

	$columns = "firstname, lastname, contacts, mobile_number, passcode";

	$values = array(
		array("Jack", "Jones", serialize(array(1, 2)), "07807637447", "1234"),
		array("Bob", "Jenkins", serialize(array(1)), "07807654812", "4321"),
		array("Ahri", "Valentine", serialize(array(2)), "07647034654", "0987")
	);

	for($i =  0; $i < sizeof($values); $i++) {
		add("Customer", $columns, $values[$i]);
	}


?>