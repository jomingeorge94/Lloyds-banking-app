<?php

	/**
	 *
	 * @file error_lib.php
	 * @author Artemiy Bozhenok
	 * @date 11/02/2015
	 *
	 * A library of definitions which are sent through the http
	 * to the java code so the app can respond appropriatly
	 *
	 */

	define('SUCCESS', '0');
	define('WRONG_PASSCODE', '1');
	define('NEW_USER', '2');
	define('ERROR', '3');
	define('NO_INTERNET_CONNECTION', '4');
	define('CONNECTION_TO_DB_ERROR', '5');
	define('IMEI_DO_NOT_MATCH', '6');
	define('ONLINE_PASSWORD_DO_NOT_MATCH', '7');
	define('SUCCESS_MOBILE_BANKING', '8');
	define('SUCCESS_FIRST_TIME', '9');
	define('ADD_ENTRY_COMPLETE', '10');
	define('TRANSFER_SUCCESSFUL', '11');
	define('BUDGET_FINISHED', '12');
	define('PASSCODE_CHANGED', '13');

?>