<?php
if(!isset($_POST['branch_name']) || !isset($_POST['addr']) || !isset($_POST['phone_no']) || !isset($_POST['branch_login_id']) || !isset($_POST['branch_login_passwd']))
{		
		echo "<script>alert('모든 항목을 입력해주세요.');history.back();</script>";
		exit;
}

$branch_name = $_POST['branch_name'];
$addr = $_POST['addr'];
$phone_no = $_POST['phone_no'];
$branch_login_id = $_POST['branch_login_id'];
$branch_login_passwd = $_POST['branch_login_passwd'];

$conn = oci_connect('maincomp', 'elqlrhkwp', '52.231.78.225/mainComp', 'UTF8');

if(!$conn) {
  echo "No Connection ".oci_error();
  exit;
}

$query1 = "SELECT MAX(BRANCH_NO) FROM BRANCH";
$result1 = oci_parse($conn, $query1);
$success = oci_execute($result1);

if(!isset($success)) {
        echo "<script>alert('error');history.back();</script>";
		exit;
}
$row = oci_fetch_assoc($result1);
$branch_no = $row['MAX(BRANCH_NO)'] + 1;

$query2 = "INSERT INTO BRANCH VALUES(:branch_no, :branch_name, :addr, :phone_no, :branch_login_id, :branch_login_passwd)";
$result2 = oci_parse($conn, $query2);

oci_bind_by_name($result2, ":branch_no", $branch_no);
oci_bind_by_name($result2, ":branch_name", $branch_name);
oci_bind_by_name($result2, ":addr", $addr);
oci_bind_by_name($result2, ":phone_no", $phone_no);
oci_bind_by_name($result2, ":branch_login_id", $branch_login_id);
oci_bind_by_name($result2, ":branch_login_passwd", $branch_login_passwd);
$success = oci_execute($result2);
oci_free_statement($result1);
oci_free_statement($result2);
oci_close($conn);

if(!isset($success)) {
        echo "<script>alert('error');history.back();</script>";
		exit;
}

echo "<script>alert('지점 추가 성공');</script>";
echo("<script>location.replace('./manager_3.php');</script>"); 


?>