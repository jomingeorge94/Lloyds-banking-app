
<?php

	/**
	 *
	 * @file deals.php
	 * @author Artemiy Bozhenok
	 * @date 10/02/2015
	 *
	 * Class resembles an entity in the database
	 *
	 */

	class Deals {
		//primary key
		public $did = "";
		//foreign key
		//identifies the publisher of the deal
		public $author = "";
		//date of publish
		public $date = "";
		//location of the deal
		public $location = "";
		//the company that is supplying the deal
		public $company = "";
		//subject of the deal
		public $subject = "";
		//when the deal ends
		public $deadline = "";
		//description of the deal
		public $text = "";
		//a picture of the deal
		public $picture = "";
		//numerical value of the deal
		public $leaderboard = "";
		//deals category - e.g. food
		public $category = "";
	}

?>