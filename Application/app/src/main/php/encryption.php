
<?php

	/**
	 *  
	 * @file encryption.php
	 * @author Artemiy Bozhenok
	 * @date 01/02/2015
	 *
	 * Uses a AES-256 bit cipher to encrypt data and then uses 2 layers of encryption
	 * for added security. ECB (Electronic Codebook) and CBC (Cipher Blocking Chaining)
	 * 
	 * data -> ECB -> data (ECB encrypt) -> CBC -> encrypted data (2 layer)
	 * 
	 */

	define('KEY_LENGTH', '8');

	/**
	 * 
	 * The function generates a key of length 8 and if successful it will
	 * return the hex of that key. Otherwise a manually generated key will be
	 * created and converted
	 * 
	 * @return key, a random hex value
	 */

	function keygen() {

		//generate a key
		for($i = 0; $i < KEY_LENGTH; $i++) {
			//append a better rand number
			$key .= mt_rand(0, 9);
		}

		//convert to hex
		$key = bin2hex($key);

		return $key;
	}

	/**
	 * 
	 * Uses 128-bit AES encryption cipher with CBC mode to encrypt the data
	 * 
	 * @param data - string to be encrypted
	 * @return string literal of the encryption
	 */

	function encrypt($data) {
		//generate keys for data
		$key_ecb = pack('H*', keygen());
		$key_cbc = pack('H*', keygen());

		//Encrypt the data using simple ECB mode
		$p_encryption = mcrypt_encrypt(MCRYPT_RIJNDAEL_128, $key_ecb, $data, MCRYPT_MODE_ECB);

		//get size of iv depending on the method used
		$iv_size = mcrypt_get_iv_size(MCRYPT_RIJNDAEL_128, MCRYPT_MODE_CBC);
		//creates random iv
		$iv = mcrypt_create_iv($iv_size, MCRYPT_RAND);

		//encrypt the partial encryption using CBC
		$encrypted_data = mcrypt_encrypt(MCRYPT_RIJNDAEL_128, $key_cbc, $p_encryption, MCRYPT_MODE_CBC, $iv);

		//prepend the keys and iv to the encrypted data
		$encrypted_data = $iv . $key_ecb . $key_cbc . $encrypted_data;

		//return the result a result that can be represented as a string
		return base64_encode($encrypted_data);
	}

	/**
	 * 
	 * This returns the string literal back to data which the cipher can understand
	 * and the it decrypts the data using the iv and the two keys within the encrypted data
	 * 
	 * @param data - string to be encrypted
	 * @return string literal of the encryption
	 */

	function decrypt($d_data) {
		//decode the data from a string
		$cipher_data = base64_decode($d_data);

		//gets the size of iv
		$iv_size = mcrypt_get_iv_size(MCRYPT_RIJNDAEL_128, MCRYPT_MODE_CBC);

		//retrieves the iv of the data
		$iv = substr($cipher_data, 0, $iv_size);

		//gets the ecb key
		$key_ecb = substr($cipher_data, $iv_size, KEY_LENGTH);

		//gets the cbc key
		$key_cbc = substr($cipher_data, ($iv_size + KEY_LENGTH), KEY_LENGTH);

		//get the actual data
		$cipher_data = substr($cipher_data, ($iv_size + KEY_LENGTH * 2));

		//decrypt the top layer first (CBC)
		$p_decryption = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $key_cbc, $cipher_data, MCRYPT_MODE_CBC, $iv);
		
		//decrypt the first layer (ECB)
		$data = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $key_ecb, $p_decryption, MCRYPT_MODE_ECB);
		
		return $data;
	}

?>