package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmObject;

public class Matakuliah extends RealmObject {
    private int matakuliahID;
    private String Nama;
    private String Prodi;
    private int SKS;
    private Prodi prodi;
    public Matakuliah(){

    }

    public Matakuliah(int matakuliahID, String nama, String prodi, int sks, Prodi prodi1) {
        this.matakuliahID = matakuliahID;
        Nama = nama;
        Prodi = prodi;
        this.prodi = prodi1;
        this.SKS = sks;
    }

    public int getMatakuliahID() {
        return matakuliahID;
    }
    public void setMatakuliahID(int matakuliahID) {
        this.matakuliahID = matakuliahID;
    }

    public int getSKS() {
        return SKS;
    }

    public void setSKS(int SKS) {
        this.SKS = SKS;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getProdi() {
        return Prodi;
    }

    public void setProdi(Prodi prodi) {
        this.prodi = prodi;
    }

    public void setProdi(String prodi) {
        Prodi = prodi;
    }
}
