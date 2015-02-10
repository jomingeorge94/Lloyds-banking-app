
<?php

	/**
	 *
	 * @file accounts.php
	 * @author Artemiy Bozhenok
	 * @date 10/02/2015
	 * @version 1.0.0
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Accounts {
		//Current, ISA etc..
		public $type_of_account = "";
		//primary key - identifies a specific account
		public $account_number = "";
		//foreign key - connects with uid - customer has same SC for all accounts
		public $sortcode = "";
		//Doesn't include the overdraft
		public $total_money = "";
		//Includes the overdraft
		public $available_money = "";
		//Overdraft limit
		public $overdraft = "";
	}

?>