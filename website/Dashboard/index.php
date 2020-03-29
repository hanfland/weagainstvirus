<?php
session_start();
if(isset($_SESSION['loggedin'])){
    header("Location: dashboard.php");
    exit();
} else {
    header("Location: login.php");
    exit();
}
?>
