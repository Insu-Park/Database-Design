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

$query = "SELECT ITEM_NAME, ITEM_PER_PRICE, ITEM_REC_PRICE, EXDATE_IMPT_FLAG FROM ITEM";
$result = oci_parse($conn, $query);
$success = oci_execute($result);
?>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <!--물품관리-->
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
	      <a href="customer_0.php" class="list-group-item list-group-item-action bg-light">-물품목록-</a>
          <a href="customer_1.php" class="list-group-item list-group-item-action bg-light">포인트변경기록확인</a>
          <a href="customer_3.php" class="list-group-item list-group-item-action bg-light">회원탈퇴</a>

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
        <h1 class="mt-4">-물품관리-</h1>
			<table class="table table-striped" style="margin-left:5px">
            <caption>
              <!--<h2 class="well">주문내역 테이블</h2>-->
            </caption>
            <thead>
              <tr>
                <th>물품명</th>
                <th>물품 개당 가격</th>
                <th>물품 권장 가격</th>
				<th>유통기한 중요여부</th>
              </tr>
            </thead>
            <tbody>
			<?php
			while($row = oci_fetch_array($result,OCI_ASSOC)){
				echo("<tr><td>$row[ITEM_NAME]</td>
				<td>$row[ITEM_PER_PRICE]</td>
				<td>$row[ITEM_REC_PRICE]</td>
				<td>$row[EXDATE_IMPT_FLAG]</td></tr>");
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