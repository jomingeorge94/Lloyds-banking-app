
<?php

	/**
	 *
	 * @file transactions.php
	 * @author Artemiy Bozhenok
	 * @date 10/02/2015
	 * @version 1.0.0
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Transactions {
		//identifys where the money has come from
		public $to_from = "";
		//transaction amount
		public $amount = "";
		//primary key
		//unique identifier for the transaction
		public $tid = ""
		//foreign key - which relates to the uid
		public $id = "";
		//the date of the transaction
		public $date = "";
	}

?>