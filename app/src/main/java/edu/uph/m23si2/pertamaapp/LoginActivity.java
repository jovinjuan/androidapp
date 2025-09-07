package edu.uph.m23si2.pertamaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import edu.uph.m23si2.pertamaapp.api.api_response.KotaResponse;
import edu.uph.m23si2.pertamaapp.api.api_response.ProvinsiResponse;
import edu.uph.m23si2.pertamaapp.api.api_service.KotaService;
import edu.uph.m23si2.pertamaapp.api.api_service.ProvinsiService;
import edu.uph.m23si2.pertamaapp.model.KRS;
import edu.uph.m23si2.pertamaapp.model.KRS_Detail;
import edu.uph.m23si2.pertamaapp.model.Kota;
import edu.uph.m23si2.pertamaapp.model.Mahasiswa;
import edu.uph.m23si2.pertamaapp.model.Matakuliah;
import edu.uph.m23si2.pertamaapp.model.Prodi;
import edu.uph.m23si2.pertamaapp.model.Provinsi;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtNama, edtPassword;
    Spinner spi_provinsi, spi_kota;
    List<Provinsi> provinsiList = new ArrayList<>();
    List<String> namaprovinsi = new ArrayList<>();
    List<Kota> kotaList = new ArrayList<>();
    List<String> namakota = new ArrayList<>();
    ArrayAdapter<String> adapter,adapter_kota;
    String kodeprovinsi;

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
        initData();

        btnLogin = findViewById(R.id.btnLogin);
        edtNama = findViewById(R.id.edtNama);
        edtPassword = findViewById(R.id.edtPassword);
        spi_provinsi = findViewById(R.id.spi_provinsi);
        spi_kota = findViewById(R.id.spi_kota);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,namaprovinsi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spi_provinsi.setAdapter(adapter);

        adapter_kota = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,namakota);
        adapter_kota.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spi_kota.setAdapter(adapter_kota);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDashboard();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wilayah.id")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProvinsiService provinsiService = retrofit.create(ProvinsiService.class);
        provinsiService.getProvinsi().enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    provinsiList = response.body().getData();
                    namaprovinsi.clear();

                    for(Provinsi p : provinsiList){
                        Log.d("Provinsi", p.getName());
                        namaprovinsi.add(p.getName());
                    }
                    adapter.notifyDataSetChanged();
                    spi_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Provinsi selected = provinsiList.get(position);
                            kodeprovinsi = selected.getCode();
                            getKotaData(kodeprovinsi);
                            Log.d("Provinsi",selected.getCode()+ "-" + selected.getName());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Gagal :"+t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }
    public void getKotaData(String kodeprovinsi){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wilayah.id")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KotaService kotaService = retrofit.create(KotaService.class);
        kotaService.getKota(kodeprovinsi).enqueue(new Callback<KotaResponse>(){
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    kotaList = response.body().getData();
                    namakota.clear();

                    for(Kota k : kotaList){
                        Log.d("Kota",k.getNama());
                        namakota.add(k.getNama());
                    }
                    adapter_kota.notifyDataSetChanged();
                    spi_kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Kota selected = kotaList.get(position);
                            Log.d("Kota",selected.getCode()+ "-"+ selected.getNama());
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Gagal :"+t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }
    public void initData(){
        clearDatabase();
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

            Matakuliah matIOT = r.createObject(Matakuliah.class,1);
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

            KRS_Detail detail_krs_matIOT = r.createObject(KRS_Detail.class,1);
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
    public void clearDatabase() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            r.deleteAll();
        });
    }
}