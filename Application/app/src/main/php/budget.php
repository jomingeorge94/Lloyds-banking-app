
<?php

	/**
	 *
	 * @file budget.php
	 * @author Artemiy Bozhenok
	 * @date 10/02/2015
	 * @version 1.0.0
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Budget {
		//Primary key
		public $bid = "";
		//The date that the budget was set for
		public $dateofbudget = "";
		//The budget figure
		public $amount = "";
		//Total spending
		public $spend = "";
		//Category budget figure
		public $travel_amount = "";
		public $beauty_and_hygiene_amount = "";
		public $entertainment_amount = "";
		public $home_amount = "";
		public $other_amount = "";
		public $clothes_amount = "";
		public $leisurely_activities_amount = "";
		public $bills_amount = "";
		public $savings_amount = "";
		public $rent_amount = "";
		public $groceries_amount = "";
		public $eating_out_amount = "";
		//Category spending figure
		public $travel_spend = "";
		public $beauty_and_hygiene_spend = "";
		public $entertainment_spend = "";
		public $home_spend = "";
		public $other_spend = "";
		public $clothes_spend = "";
		public $leisurely_activities_spend = "";
		public $bills_spend = "";
		public $savings_spend = "";
		public $rent_spend = "";
		public $groceries_spend = "";
		public $eating_out_spend = "";
		//week days
		public $monday_spend = "";
		public $tuesday_spend = "";
		public $wednesday_spend = "";
		public $thursday_spend = "";
		public $friday_spend = "";
		public $saturday_spend = "";
		public $sunday_spend = "";
	}

?>