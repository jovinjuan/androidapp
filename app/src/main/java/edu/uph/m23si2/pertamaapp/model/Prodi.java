package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Prodi extends RealmObject {
    @PrimaryKey
    private int prodiID;
    private String nama;
    private String fakultas;
    public Prodi(){

    }

    public Prodi(int prodiID, String nama, String fakultas) {
        this.prodiID = prodiID;
        this.nama = nama;
        this.fakultas = fakultas;
    }

    public int getProdiID() {
        return prodiID;
    }

    public void setProdiID(int prodiID) {
        this.prodiID = prodiID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
}

