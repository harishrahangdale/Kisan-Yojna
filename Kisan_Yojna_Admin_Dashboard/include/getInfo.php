<?php
 require_once('dbcon.php');
 
 $sql = "select webinfo from ky_info where id='6'";
 
 $res = mysqli_query($conn,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 	
 	//$obj = base64_encode($row['webinfo']);
 array_push($result,array('webinfo'=> base64_encode($row['webinfo'])));
 }
 
 
 echo json_encode($result);
 //See this part need to do in Android there is a base64 decoder available.
 	//print '<br>';
	//$obj = base64_decode($obj);
	//echo $obj;
//
 mysqli_close($conn);