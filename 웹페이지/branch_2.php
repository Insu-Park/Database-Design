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

$query = "SELECT ITEM_NAME, QTY, ODR_TIME, ODR_STATUS_CD FROM ITEM, ODR, ODITEM
	WHERE BRANCH_NO IN (SELECT BRANCH_NO FROM BRANCH WHERE BRANCH_LOGIN_ID = :user_id)
	AND ODR.ODR_NO = ODITEM.ODR_NO
	AND ODITEM.ITEM_NO = ITEM.ITEM_NO";
$result = oci_parse($conn, $query);
oci_bind_by_name($result, ":user_id", $user_id);
$success = oci_execute($result);


?>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <!--주문내역-->
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
        <a href="branch_1.php" class="list-group-item list-group-item-action bg-light">물품신청</a>
        <a href="branch_2.php" class="list-group-item list-group-item-action bg-light">-주문내역-</a>
        <a href="branch_3.php" class="list-group-item list-group-item-action bg-light">반품신청</a>
        <a href="branch_4.php" class="list-group-item list-group-item-action bg-light">반품내역</a>

      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="branch_main.php">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
        <h1 class="mt-4">-주문내역-</h1>
        <table class="table table-striped" style="margin-left:5px">
            <caption>
              <!--<h2 class="well">주문내역 테이블</h2>-->
            </caption>
            <thead>
              <tr>
                <th>물품명</th>
                <th>수량</th>
                <th>주문신청일시</th>
				<th>주문상태</th>
              </tr>
            </thead>
            <tbody>
			<?php
			while($row = oci_fetch_array($result,OCI_ASSOC)){
				echo("<tr><td>$row[ITEM_NAME]</td>
				<td>$row[QTY]</td>
				<td>$row[ODR_TIME]</td>
				<td>$row[ODR_STATUS_CD]</td></tr>");
			}
			oci_free_statement($result);
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
