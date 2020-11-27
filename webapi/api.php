<?php

$nik = $_GET['nik'];

//silahkah di kreasi di hubungkan ke database mysql

$data = array();
if($nik == "1111"){

    $data['response'] = true;
    $data['nama'] = "eko";
    $data['alamat'] = "lampung";


}else{
    $data['response'] = false;
    

}

header('Content-Type: application/json');
echo json_encode($data);