package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class KRS_Detail extends RealmObject {

    private int krs_detailID;

    private KRS krs;
    private int nilai;
    private Matakuliah matakuliah;
    private String namaDosen;

    public KRS_Detail(){

    }

    public KRS_Detail(int krs_detailID, KRS krs, int nilai, Matakuliah matakuliah, String namaDosen) {
        this.krs_detailID = krs_detailID;
        this.krs = krs;
        this.nilai = nilai;
        this.matakuliah = matakuliah;
        this.namaDosen = namaDosen;
    }

    public int getKrs_detailID() {
        return krs_detailID;
    }

    public void setKrs_detailID(int krs_detailID) {
        this.krs_detailID = krs_detailID;
    }

    public KRS getKrs() {
        return krs;
    }

    public void setKrs(KRS krs) {
        this.krs = krs;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public Matakuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }
}
