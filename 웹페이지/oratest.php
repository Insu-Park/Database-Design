<?php

function xmp_print_r($arr) { echo '<xmp>'; print_r($arr); echo '</xmp>'; }
$conn = oci_connect('maincomp', 'elqlrhkwp', '52.231.78.225/mainComp', 'UTF8');
$query = "select * from custom";
$stid = oci_parse($conn, $query) or die('oci parse error: '.oci_error($conn));
if(oci_execute($stid) === false) die("oci query error [ $query ] message : ".oci_error($stid));
oci_fetch_all($stid, $arr, null, null, OCI_FETCHSTATEMENT_BY_ROW);
oci_close($conn);
xmp_print_r($arr);


?>
