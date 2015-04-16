<?php

	/**
	 *
	 * @file config_db.php
	 * @author Artemiy Bozhenok
	 * @date 05/02/2015
	 *
	 * A configuration file for database connection
	 *
	 */

	require('error_lib.php');

	//DB constants
	define('SERVER', "localhost");
	define('DATABASE', "abunitie_t2022t1");
	define('DB_USERNAME', "abunitie_t2022t1");
	define('DB_PASSWORD', "=BoxNext");

	/**
	 * Connects to a database. Note * constants must be changed if connecting to foreign server
	 *
	 * @param db - name of the database to connect to
	 * @return connection resource to a mysql database
	 */

	function connect() {
		//identify if internet connection is established with device
		$isConnected = @fsockopen("www.google.com", 80);
		//check for internet connection
		if(!$isConnected) {
			//error
			echo NO_INTERNET_CONNECTION;
			return false;
		}

		//connect to the database
		$db_conn = mysqli_connect(SERVER, DB_USERNAME, DB_PASSWORD, DATABASE);

		//does the connection fail?
		if(mysqli_connect_error()) {
			//return a JSON object to java indicating failure
			echo CONNECTION_TO_DB_ERROR;
			return false;
		}

		//otherwise continue
		return $db_conn;	
	}

	/**
	 * Closes the connection to given mysql database
	 *
	 * @param db_conn - mysql connection resource
	 */

	function close($db_conn) {
		//close the connection to the databse safely
		$isClosed = mysqli_close($db_conn);

		//is the closure of the database successful?
		if(!$isClosed) {
			echo CONNECTION_TO_DB_ERROR;
		}

	}
?>