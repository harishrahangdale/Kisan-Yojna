<?php
 require_once('dbcon.php');
 
 $sql = "select webinfo from ky_info where state='Hindi'";
 
 $res = mysqli_query($conn,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('webinfo'=> $row['webinfo']));
 }
 
 echo json_encode($result);
 
 mysqli_close($conn);