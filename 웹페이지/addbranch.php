<?php
session_start();

if(!isset($_SESSION['user_id']) || !isset($_SESSION['user_pw'])) {
	echo "<meta http-equiv='refresh' content='0;url=login.php'>";
	exit;
}
$user_id = $_SESSION['user_id'];
$user_pw = $_SESSION['user_pw'];


?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>addbranch</title>
</head>

<body>
    <h1 style="text-align: center">UOS 25</h1>
    <hr size="60px">
    <form method="POST" action='addbranch_ok.php' style="margin-left: 500px; margin-top: 50px">
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="지점이름" name='branch_name' aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="주소" name='addr' aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="전화번호" name='phone_no' aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="지점로그인ID" name='branch_login_id' aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="password" class="form-control" placeholder="비밀번호" name='branch_login_passwd' aria-describedby="basic-addon2">
        </div>
        <br>
        <p><input type="submit" value='지점추가' role="button" style="margin-left: 80pt" a class="btn btn-primary btn-lg"></a></p>
    </form>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>