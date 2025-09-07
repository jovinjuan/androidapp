package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KRS extends RealmObject {
    @PrimaryKey
    private int krsID;
    private Mahasiswa mahasiswa;
    private String semesterAkademik;

    private RealmList<KRS_Detail> krs_details;

    public KRS(){

    }

    public KRS(int krsID, Mahasiswa mahasiswa, String semesterAkademik, RealmList<KRS_Detail> krs_details) {
        this.krsID = krsID;
        this.mahasiswa = mahasiswa;
        this.semesterAkademik = semesterAkademik;
        this.krs_details = krs_details;
    }

    public int getKrsID() {
        return krsID;
    }

    public void setKrsID(int krsID) {
        this.krsID = krsID;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public String getSemesterAkademik() {
        return semesterAkademik;
    }

    public void setSemesterAkademik(String semesterAkademik) {
        this.semesterAkademik = semesterAkademik;
    }

    public RealmList<KRS_Detail> getKrs_details() {
        return krs_details;
    }

    public void setKrs_details(RealmList<KRS_Detail> krs_details) {
        this.krs_details = krs_details;
    }
}
