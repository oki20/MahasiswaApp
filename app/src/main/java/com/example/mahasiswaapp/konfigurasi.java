package com.example.mahasiswaapp;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.0.4/crud_poltekgt/tambahMhs.php";
    public static final String URL_GET_ALL = "http://192.168.0.4/crud_poltekgt/tampilSemuaMhs.php";
    public static final String URL_GET_EMP = "http://192.168.0.4/crud_poltekgt/tampilMhs.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.0.4/crud_poltekgt/updateMhs.php";
    public static final String URL_DELETE_EMP = "http://192.168.0.4/crud_poltekgt/hapusMhs.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NIM = "nim";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_PROG_STUDI = "prog_studi";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NIM = "nim";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PROG_STUDI = "prog_studi";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
