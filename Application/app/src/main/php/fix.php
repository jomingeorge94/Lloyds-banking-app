<?php
	require('db.php');

	$current_spend = fetch("Budget", "spend", "100000003", BUDGET);
	modify("Budget", "spend", "154.56", "100000003", BUDGET);

?>