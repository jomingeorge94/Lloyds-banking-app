
<?php

	/**
	 *
	 * @file customer.php
	 * @author Artemiy Bozhenok
	 * @date 07/02/2015
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Customer {
		//primary key - used for sortcode
		//uid will always have a length of 6 format: xx-xx-xx
		public $uid = "";
		//names of the customer
		public $firstname = "";
		public $lastname = "";
		//serialized array
		//the array contains the uid of the contacts
		public $contacts = "";
		//set passcode used to login in
		//length of 4 format: xxxx
		public $passcode = "";
		//imei number of the mobile the user is using
		public $mobile_banking = "";
		//users online password
		public $online_password = "";
	}

?>