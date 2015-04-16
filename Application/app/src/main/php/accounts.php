
<?php

	/**
	 *
	 * @file accounts.php
	 * @author Artemiy Bozhenok
	 * @date 10/02/2015
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Accounts {
		//Current, ISA etc..
		public $type_of_account = "";
		//foreign key - references UID
		public $aid = "";
		//primary key - identifies a specific account
		public $account_number = "";
		//branch code
		public $sortcode = "";
		//Doesn't include the overdraft
		public $total_money = "";
		//Includes the overdraft
		public $available_money = "";
		//Overdraft limit
		public $overdraft = "";
	}

?>