<?php

if (!isset($_POST['username'], $_POST['password'], $_POST['login-submit']) ) {
    header("Location: ../login.php?error=emptyfields");
    exit();
}

$username = $_POST['username'];
$password = $_POST['password'];
if(empty($username) || empty($password)){
    header("Location: ../login.php?error=emptyfields");
    exit();
}

require "dbh.inc.php";
$sql = 'SELECT classid,password FROM users WHERE username=?;';
$stmt = mysqli_stmt_init($conn);

if(!mysqli_stmt_prepare($stmt, $sql)){
    
    header("Location: ../login.php?error=sqlerror");
    exit();
} else {
    mysqli_stmt_bind_param($stmt, "s", $username);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    
    if($row = mysqli_fetch_assoc($result)){
        //$password_check = password_verify($password, $row['password']);
        if($password === $row['password']){
            
            
            
            
            $stmt_class = mysqli_stmt_init($conn);
            
            if(!mysqli_stmt_prepare($stmt_class, "SELECT name FROM classes WHERE id=?;")){
                header("Location: ../login.php?error=sqlerror2");
                exit();
            } else {
                mysqli_stmt_bind_param($stmt_class, "i", $row['classid']);
                mysqli_stmt_execute($stmt_class);
                $result2 = mysqli_stmt_get_result($stmt_class);
                if($row2 = mysqli_fetch_assoc($result2)){
                    
                    
                    session_start();
                    $_SESSION['username'] = $row['username'];
                    $_SESSION['classid'] = $row['classid'];
                    $_SESSION['class'] = $row2['name'];
                    $_SESSION['loggedin'] = true;
                    $_SESSION['logintime'] = true;
                    header("Location: ../dashboard.php?success=login");
                    exit();
                    
                } else {
                    header("Location: ../login.php?error=class_null");
                    exit();
                }
            }
            
            
            
        } else {
            header("Location: ../login.php?error=password");
            exit();
        }
    } else {
        header("Location: ../login.php?error=nouser&old=" . $username);
        exit();
    }
}
?>