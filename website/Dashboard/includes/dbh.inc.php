<?php

function console_log( $data ){
  echo '<script>';
  echo 'console.log('. json_encode( $data ) .')';
  echo '</script>';
}

error_reporting(E_ALL & ~E_NOTICE);
//session_start();
// Change this to your connection info.
$SERVERNAME = 'localhost';
$DB_USERNAME = 'root';
$DB_PASSWORD = '';
$DATABASE = 'example';
// Try and connect using the info above.
$conn = mysqli_connect($SERVERNAME, $DB_USERNAME, $DB_PASSWORD, $DATABASE);
if (mysqli_connect_errno()) {
	// If there is an error with the connection, stop the script and display the error.
	exit('Failed to connect to MySQL: ' . mysqli_connect_error());
}

if(!$conn){
    die("Connection failed: ".mysqli_connect_error());
}

?>