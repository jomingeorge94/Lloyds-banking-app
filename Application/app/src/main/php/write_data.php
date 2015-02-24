
<?php

	require('config_db.php');
	require('db.php');

	connect();

	$columns = array(

	);

	$values = array(

	);

	for($i =  0; $i < 8; $i++) {
		add("test_table", $columns, $values);
	}


?>