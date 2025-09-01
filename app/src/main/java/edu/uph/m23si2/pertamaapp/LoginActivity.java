package edu.uph.m23si2.pertamaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.m23si2.pertamaapp.model.KRS;
import edu.uph.m23si2.pertamaapp.model.KRS_Detail;
import edu.uph.m23si2.pertamaapp.model.Mahasiswa;
import edu.uph.m23si2.pertamaapp.model.Matakuliah;
import edu.uph.m23si2.pertamaapp.model.Prodi;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtNama, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true) // sementara aktifkan untuk demo
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        btnLogin = findViewById(R.id.btnLogin);
        edtNama = findViewById(R.id.edtNama);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDashboard();
            }
        });
    }
    public void initData(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Number maxId = r.where(Mahasiswa.class).max("studentID");
            Prodi prodiSI = r.createObject(Prodi.class,0);
            prodiSI.setFakultas("Fakultas Teknologi Informasi");
            prodiSI.setNama("Sistem Informasi");

            Matakuliah matMobile = r.createObject(Matakuliah.class,0);
            matMobile.setNama("Pemrograman Mobile Lanjut");
            matMobile.setSKS(3);
            matMobile.setProdi(prodiSI);

            Matakuliah matIOT = r.createObject(Matakuliah.class,0);
            matIOT.setNama("Pemrograman IOT");
            matIOT.setSKS(3);
            matIOT.setProdi(prodiSI);

            Mahasiswa mahasiswa1 = r.createObject(Mahasiswa.class,0);
            mahasiswa1.setNama("Jovin");
            mahasiswa1.setProdi("Sistem Informasi");

            KRS_Detail detail_krs_matMobile = r.createObject(KRS_Detail.class,0);
            detail_krs_matMobile.setMatakuliah(matMobile);
            detail_krs_matMobile.setNamaDosen("Ade Maulana");
            detail_krs_matMobile.setNilai(100);

            KRS_Detail detail_krs_matIOT = r.createObject(KRS_Detail.class,0);
            detail_krs_matIOT.setMatakuliah(matIOT);
            detail_krs_matIOT.setNamaDosen("Ade Maulana");
            detail_krs_matIOT.setNilai(100);

            RealmList<KRS_Detail> detail_krs = new RealmList<>();
            detail_krs.add(detail_krs_matIOT);
            detail_krs.add(detail_krs_matMobile);

            KRS krsmahasiswa = r.createObject(KRS.class,0);
            krsmahasiswa.setMahasiswa(mahasiswa1);
            krsmahasiswa.setSemesterAkademik("Ganjil 2025/2026");
            krsmahasiswa.setKrs_details(detail_krs);
            Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
        });

    }
    public void toProfil(){
        Intent intent = new Intent(this,ProfilActivity.class);
        intent.putExtra("nama",edtNama.getText().toString());
        startActivity(intent);
    }
    public void toDashboard(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }
}