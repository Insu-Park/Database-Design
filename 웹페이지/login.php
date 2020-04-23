<!DOCTYPE html>
<?php
session_start();
if(isset($_SESSION['user_id']) && isset($_SESSION['user_pw'])) {

	$user_id = $_SESSION['user_id'];
	$user_pw = $_SESSION['user_pw'];
	$user_type = $_SESSION['user_type'];
	
	if($user_id == 'admin' and $user_type == 'customer'){
		echo("<script>location.replace('./manager_main.php');</script>"); 
	}
	else if($user_type == 'customer'){
		echo("<script>location.replace('./customer_main.php');</script>"); 
	}
	else {
		echo("<script>location.replace('./branch_main.php');</script>"); 
	}
}
?>
<html lang="en">

<head>

    <meta charset="utf-8">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>

    </style>

    <script>

    </script>
</head>
	
<body>
    <div class="jumbotron" style="text-align:center">
        <h1>UOS 25</h1>
        <!--아이디&비밀번호 입력-->
        <form method="POST" action='login_ok.php' style="margin-left:38%" >
            <div class="input-group" style="width: 300px;">
                <input type="text" name='user_id' class="form-control" placeholder="ID" aria-describedby="basic-addon2">
            </div>
            <div class="input-group" style="width: 300px">
                <input type="password" name='user_pw' class="form-control" placeholder="PW" aria-describedby="basic-addon2">
            </div>
            <br>
			<div class="input-group" style="width: 300px">
				<input type="radio" name="user_type" value="customer" checked="checked"/> 고객
				<input type="radio" name="user_type" value="branch" /> 점주
			</div>
			<br>
            <p><input type='submit' value='로그인' role="button" style="margin-right:62%"></p>
        </form>
        <p><a class="btn" href="signup.php" role="button" style="text-align:center">회원가입</a></p>
    </div>
    <!-- Bootstrap core JavaScript -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>