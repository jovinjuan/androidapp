package com.uph23.edu.jovinjuanlieapp;

import android.os.Bundle;
import android.view.Gravity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ProfilActivity extends AppCompatActivity {
    EditText edtNama, edtProdi, edtNBD, edtNMB;
    Button btnSubmit;
    TextView txvHasil;

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
        final Button button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String prodi = edtProdi.getText().toString();
                String NBD = edtNBD.getText().toString();
                String NMB = edtNMB.getText().toString();
                int nbd = 0;
                int nmb = 0;
                String fakultas;
                String ipk;
                if(prodi.toLowerCase().equals("sistem informasi") || prodi.toLowerCase().equals("informatika")){
                    fakultas = "Fakultas Teknologi Informasi";
                } else if (prodi.toLowerCase().equals("hukum")) {
                    fakultas = "Fakultas Hukum";
                }
                else if(prodi.toLowerCase().equals("akuntansi") || prodi.toLowerCase().equals("manajemen")){
                    fakultas = "Fakultas Ekonomi dan Bisnis";
                }
                else{
                    fakultas = "Fakultas Tidak Ditemukan";
                }
                txvHasil.setText(nama + "\n" + prodi + "\n" + fakultas);
                edtNama.setText("");
                edtProdi.setText("");

            }
        });
        edtNama = findViewById(R.id.edtNama);
        edtProdi = findViewById(R.id.edtProdi);
        btnSubmit = findViewById(R.id.btnSubmit);
        txvHasil = findViewById(R.id.txvHasil);

        edtNama.setText(getIntent().getStringExtra("name").toString());
        edtProdi.setText(getIntent().getStringExtra("prodi").toString());

    }
}