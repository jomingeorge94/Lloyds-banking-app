
<?php
	/**
	 *
	 * @file db.php
	 * @author Artemiy Bozhenok
	 * @date 02/02/2015
	 * @version 0.1.5
	 *
	 * The class handles database connections and ensure the connection is
	 * successfully established, otherwise it will handle any errors that occur
	 *
	 */

	require('config_db.php');
	require('encryption.php');

	/**
	 *
	 * This function sends a query to the database, then the values variable will then 
	 * be split into an array and used as keys. the next function takes the processed
	 * query and fetches data and returns an array which is then decrypted and placed
	 * into a key-value pair array which is returned.
	 *
	 * @param values - the variables to fetch, format e.g. name, age, ... , N
	 * @param id - unique identifier of user (O(1) - searching)
	 * @return array of key-pair values
	 */

	function fetch($db_table, $values, $uid) {
		//opens connection to database
		$db_conn = connect();

		//SQL query to fetch record
		$sql = "SELECT " . $values . 
			   " FROM " . $db_table . 
			   " WHERE uid = '" . $uid . "'";

		$result = mysqli_query($db_conn, $sql);

		if(!$result) {
			//return error
			echo "failed";
			return false;
		}

		$arr_values = explode(",", $values);

		$row = mysqli_fetch_array($result, MYSQLI_NUM);

		if(is_null($row[0])) {
			//error
			echo "Record not found";
			return false;
		}

		$f_values = array();

		for($i = 0; $i < sizeof($arr_values); $i++) {
			$f_values[$arr_values[$i]] = decrypt($row[$i]);
		}

		close($db_conn);

		//returns one value as a string or an array
		return (sizeof($f_values) < 2) ? trim($f_values[$values]) : $f_values;
	}

	/**
	 * 
	 * Insert a new row into the table given the columns
	 *
	 * @param db_table - table to insert into
	 * @param columns - identified columns of the table
	 * @param values - array of values in the order provided by the middle section of SQL
	 * @return bool, true = successful insertion otherwise false
	 */

	function add($db_table, $columns, $values) {
		$db_conn = connect();
		$v = "";

		//appends neccessary values to v
		for($i = 0; $i < sizeof($columns); $i++) {
			$v .= encrypt($values[$i]) . ($i != (sizeof($columns)-1)) ? '", "' : "";
		}

		$sql = 'INSERT INTO ' . $db_table .
			   '(' . $columns . ') ' .
			   'VALUES ("' . $v . '")';
		
		$success = mysqli_query($db_conn, $sql);
		
		if(!$success) {
			echo '<p>Inserting to database has failed</p>';
			close($db_conn);
			return $success;
		}

		close($db_conn);
		return $success;
	}

	/**
	 * 
	 * As the function says. Simply modifys a single columns value given the uid
	 * 
	 * @param column - column to modify
	 * @param db_table - table to modify
	 * @param value - new value
	 * @param uid - unique identify for quick retrieving
	 * @return bool, true = successful modification otherwise false
	 */

	function modify($column, $db_table, $value, $uid) {
		//connect to the 'test' database
		$db_conn = connect();
		
		//SQL for modification
		$sql = "UPDATE " . $db_table .
			   " SET " . $column . "='" . encrypt($value) .
			   "' WHERE uid = " . $uid;
		
		//get the return value of the query
		$isSuccessful = mysqli_query($db_conn, $sql);

		//checks to see if the query was successful
		if(!$isSuccessful) {
			//Return JSON object
			echo "Modifying column " . $column . " of uid " . $uid . " failed";
			//close connection to database
			close($db_conn);
			
			return $isSuccessful;
		}
		//close connection to database
		close($db_conn);
		//Return JSON object to indicate success
		return $isSuccessful;
	}

?>