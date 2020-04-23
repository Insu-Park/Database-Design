<?php
if(!isset($_POST['user_id']) || !isset($_POST['user_pw'])) exit;

$conn = oci_connect('maincomp', 'elqlrhkwp', '52.231.78.225/mainComp', 'UTF8');

if(!$conn) {
  echo "No Connection ".oci_error();
  exit;
}


$user_id = $_POST['user_id'];
$user_pw = $_POST['user_pw'];
$user_type = $_POST['user_type'];

if($user_type === 'customer'){
	$query = "SELECT COUNT(*) FROM CUSTOM WHERE CUSTOM_LOGIN_ID = :user_id AND CUSTOM_LOGIN_PASSWD = :user_pw";
	$result = oci_parse($conn, $query);
	oci_bind_by_name($result, ":user_id", $user_id);
	oci_bind_by_name($result, ":user_pw", $user_pw);
	$success = oci_execute($result);
}
else if($user_type == 'branch'){
	$query = "SELECT COUNT(*) FROM BRANCH WHERE BRANCH_LOGIN_ID = :user_id AND BRANCH_LOGIN_PASSWD = :user_pw";
	$result = oci_parse($conn, $query);
	oci_bind_by_name($result, ":user_id", $user_id);
	oci_bind_by_name($result, ":user_pw", $user_pw);
	$success = oci_execute($result);
}
$row = oci_fetch_assoc($result);
oci_free_statement($result);
oci_close($conn);

if(!isset($success)|| $row['COUNT(*)']!=1) {
        echo "<script>alert('아이디 또는 패스워드가 잘못되었습니다.');history.back();</script>";
		exit;
}


session_start();
$_SESSION['user_id'] = $user_id;
$_SESSION['user_pw'] = $user_pw;
$_SESSION['user_type'] = $user_type;

if($user_id == 'admin' and $user_type == 'customer'){
	echo("<script>location.replace('./manager_main.php');</script>"); 
}
else if($user_type == 'customer'){
	echo("<script>location.replace('./customer_main.php');</script>"); 
}
else {
	echo("<script>location.replace('./branch_main.php');</script>"); 
}

?>

<!--
<meta http-equiv='refresh' content='0;url=main.php'>
-->
