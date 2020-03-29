<?php
session_start();
if(!isset($_SESSION['loggedin'])){
    header("Location: login.php");
    exit();
}
include "includes/dbh.inc.php";
?>


<!doctype html>
<html lang="de">
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="css/files.css">
    <!-- Bootstrap core CSS -->
      <link href="/modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div id="wrapper">
            <ul class="navbar-nav sidebar bg-gradient-primary">
                <li class="sidebar-class">
                    <p class="class-text"><?php echo $_SESSION['class'] ?></p>
                    <ul class="sidebar-theme">
                        <li>
                            <p class="theme-text">Themes / Exercises</p>
                            <ul class="sidebar-task">
                                
                                <?php
                            
                                    $stmt_themes = mysqli_stmt_init($conn);
                                    if(!mysqli_stmt_prepare($stmt_themes, "SELECT name,id FROM themes WHERE classid=?;")){
                                        //header("Location: ../dashboard.php?error=sidebar_sqlerror");
                                        echo 'Sidebar konnte nicht geladen werden!';
                                        exit();
                                    } else {
                                        mysqli_stmt_bind_param($stmt_themes, "i", $_SESSION['classid']);
                                        mysqli_stmt_execute($stmt_themes);
                                        $result = mysqli_stmt_get_result($stmt_themes);
                                        if($row = mysqli_fetch_assoc($result)){
                                            $themeid = $row["id"];


                                            echo '


                                        <li><a href="dashboard.php?tasks=' . $themeid . '">' . $row["name"] . '</a></li>
                                        



                                            ';



                                        }
                                    }

                                ?>
                                
                                
                                
                                
                                
                            </ul>
                        </li>
                        <li>
                            <p class="theme-text">Q/A</p>
                            <ul class="sidebar-task">
                                <!--<li><a href="dashboard?view=tickets">Meine Fragen</a></li>-->
                                <li><a href="dashboard.php?chat=class">Chat</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <nav class="navbar bg-white topbar static-top shadow">
                        <div class="container-fluid">
                            <!-- <a href="dashboard?view=tickets" class="btn btn-primary">Frage stellen</a> -->
                        </div>
                    </nav>
                    <div class="container-fliud h-100">
                        
                        
                        <?php
                            if(isset($_GET["tasks"])){
                                include "theme.php";
                            } else if($_GET["chat"]){
                                echo '
                                <div style="width:400px;padding-top:5vh; margin:0 auto;">
                                    <iframe style="border:1px solid #ddd;height:600px;width:400px;margin:auto;" src="http://127.0.0.1:8000/#' . $_SESSION["username"] . '"></iframe>
                                </div>
                                ';
                            }
                        ?>
                    </div>
                </div>
                <footer class="sticky-footer bg-white">
                    <div class="copyright text-center my-auto">
                        <span>Copyright (c) Webseite</span>
                    </div>
                </footer>
            </div>
        </div>
    </body>
</html>

<!-- https://www.youtube.com/watch?v=LC9GaXkdxF8 -->