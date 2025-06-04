package edu.uph.m23si2.pertamaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edtNama, edtEmail, edtProdi;
    TextView txvHasil;

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtProdi = findViewById(R.id.edtProdi);
        txvHasil = findViewById(R.id.txvHasil);

        edtNama.setText(getIntent().getStringExtra("nama"));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Menampilkan hasil di txvHasil
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String prodi = edtProdi.getText().toString();
                txvHasil.setText(nama + "\n"+ email + "\nProgram Studi" + prodi + "\n" +
                        getNamaFakultas(prodi));
            }
        });
    }

    String getNamaFakultas(String prodi){
        String namaProdi = prodi.toLowerCase();
        if(namaProdi.equals("sistem informasi") || namaProdi.equals("informatika")){
            return "Fakultas Teknologi Informasi";
        }
        else if(namaProdi.equals("hukum") || namaProdi.equals("law")){
            return "Fakultas Hukum";
        }
        else if(namaProdi.equals("akuntansi") || namaProdi.equals("manajemen")||
                namaProdi.equals("perhotelan")){
            return "Fakultas Ekonomi dan Bisnis";
        }
        else
            return "Fakultas Tidak di Temukan";
    }
}