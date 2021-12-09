package com.sandiww.firebasecrud.models;
public class data_mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;
    private String key;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    // Membuat Konstruktor kosong untuk membaca data snapshot
    public  data_mahasiswa(){
    }

    // Konstruktor dengan beberapa parameter, untuk mendapatkan input data dari User
    public data_mahasiswa(String nim, String nama, String jurusan){
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }

}
