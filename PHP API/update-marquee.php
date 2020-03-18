<?php

include("config.php");

	$id_marquee = $_POST['id_marquee'];
	$isi_marquee = $_POST['isi_marquee'];

	$sql = "UPDATE marquee SET isi_marquee='$isi_marquee' WHERE id_marquee=1 ";
	$query = mysqli_query($db, $sql);

	if ($query) {
		# code...
	}else{
		die("Gagal menyimpan perubahan...");
	}

?>