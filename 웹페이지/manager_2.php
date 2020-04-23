<!DOCTYPE html>

<?php
session_start();
if(!isset($_SESSION['user_id']) || !isset($_SESSION['user_pw'])) {
	echo "<meta http-equiv='refresh' content='0;url=login.php'>";
	exit;
}
$user_id = $_SESSION['user_id'];
$user_pw = $_SESSION['user_pw'];

$conn = oci_connect('maincomp', 'elqlrhkwp', '52.231.78.225/mainComp', 'UTF8');

if(!$conn) {
  echo "No Connection ".oci_error();
  exit;
}

$query1 = "SELECT RETURN_NO, RETURN_TIME, BRANCH_NAME FROM RETURN, BRANCH
	WHERE RETURN_STATUS_CD = 'CD0601'
	AND RETURN.BRANCH_NO = BRANCH.BRANCH_NO";
	
$query2 = "SELECT RETURN.RETURN_NO, ITEM_NAME, QTY, BRANCH_NAME FROM RETURN, RTITEM, ITEM, BRANCH
	WHERE RETURN_STATUS_CD = 'CD0601'
	AND RTITEM.RETURN_NO = RETURN.RETURN_NO
	AND RTITEM.ITEM_NO = ITEM.ITEM_NO
	AND RETURN.BRANCH_NO = BRANCH.BRANCH_NO";
	
$result1 = oci_parse($conn, $query1);
$result2 = oci_parse($conn, $query2);

oci_execute($result1);
oci_execute($result2);

?>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <!--반품관리-->
  <title>UOS25</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading" style="font-size:20px"><b>UOS 25</b></div>
      <div class="list-group list-group-flush">
        <a href="manager_1.php" class="list-group-item list-group-item-action bg-light">주문관리</a>
        <a href="manager_2.php" class="list-group-item list-group-item-action bg-light">-반품관리-</a>
        <a href="manager_3.php" class="list-group-item list-group-item-action bg-light">지점관리</a>
        <a href="manager_4.php" class="list-group-item list-group-item-action bg-light">물품관리</a>

      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="manager_main.php">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                Dropdown
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout.php">로그아웃</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
      <div class="container-fluid">
        <h1 class="mt-4">-반품목록-</h1>
		<table class="table table-striped" style="margin-left:5px">
            <caption>
              <!--<h2 class="well">주문내역 테이블</h2>-->
            </caption>
            <thead>
              <tr>
                <th>주문번호</th>
                <th>반품신청일시</th>
                <th>지점이름</th>
				<th>승인</th>
              </tr>
            </thead>
            <tbody>
			<?php
			while($row = oci_fetch_array($result1,OCI_ASSOC)){
				echo("<tr><td>$row[RETURN_NO]</td>
				<td>$row[RETURN_TIME]</td>
				<td>$row[BRANCH_NAME]</td>");
				echo '<td><input type="submit" value="승인" role="button" name="array[]" /></td></tr>';
			}
			?>
            </tbody>
          </table>
      </div>
	  <div class="container-fluid">
        <h1 class="mt-4">-반품물품-</h1>
		<table class="table table-striped" style="margin-left:5px">
            <caption>
              <!--<h2 class="well">주문내역 테이블</h2>-->
            </caption>
            <thead>
              <tr>
                <th>주문번호</th>
                <th>물풀명</th>
                <th>수량</th>
				<th>지점이름</th>
              </tr>
            </thead>
            <tbody>
			<?php
			while($row = oci_fetch_array($result2,OCI_ASSOC)){
				echo("<tr><td>$row[RETURN_NO]</td>
				<td>$row[ITEM_NAME]</td>
				<td>$row[QTY]</td>
				<td>$row[BRANCH_NAME]</td></tr>");
			}
			oci_free_statement($result2);
			oci_close($conn);
			?>
            </tbody>
          </table>
      </div>
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


</body>

</html>