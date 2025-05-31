package com.uph23.edu.jovinjuanlieapp;

import static java.lang.Math.round;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    EditText nilaipertama,nilaikedua;
    Button btntambah,btnkali,btnbagi;
    TextView txvhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi
        nilaipertama = findViewById(R.id.nilaipertama);
        nilaikedua = findViewById(R.id.nilaikedua);
        btntambah = findViewById(R.id.btntambah);
        btnkali = findViewById(R.id.btnkali);
        btnbagi = findViewById(R.id.btnbagi);
        txvhasil = findViewById(R.id.txvhasil);

        // Button Tambah
        final Button button_tambah = btntambah;
        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hasiltambah = tambah();

                String hasil = String.valueOf(hasiltambah);
                txvhasil.setText(hasil);
            }
        });
        final Button button_kali = btnkali;
        button_kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hasilkali = kali();

                String hasil = String.valueOf(hasilkali);
                txvhasil.setText(hasil);
            }
        });
        final Button button_bagi = btnbagi;
        button_bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hasilbagi = bagi();

                String hasil = String.valueOf(hasilbagi);
                txvhasil.setText(hasil);
            }
        });
        };
    private int tambah(){
        String firstscore = nilaipertama.getText().toString();
        String secondscore = nilaikedua.getText().toString();

        if(firstscore.isEmpty() && secondscore.isEmpty()){
            Toast.makeText(this, "Nilai tidak boleh kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (firstscore.isEmpty()) {
            Toast.makeText(this, "Nilai pertama kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (secondscore.isEmpty()) {
            Toast.makeText(this, "Nilai kedua kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            int score1 = Integer.parseInt(firstscore);
            int score2 = Integer.parseInt(secondscore);
            int sum = score1 + score2;
            return sum;
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "Invalid Input, pastikan masukan angka !", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
    private int kali(){
        String firstscore = nilaipertama.getText().toString();
        String secondscore = nilaikedua.getText().toString();

        if(firstscore.isEmpty() && secondscore.isEmpty()){
            Toast.makeText(this, "Nilai tidak boleh kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (firstscore.isEmpty()) {
            Toast.makeText(this, "Nilai pertama kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (secondscore.isEmpty()) {
            Toast.makeText(this, "Nilai kedua kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            int score1 = Integer.parseInt(firstscore);
            int score2 = Integer.parseInt(secondscore);
            int multiply = score1 * score2;
            return multiply;
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "Invalid Input, pastikan masukan angka !", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    private int bagi(){
        String firstscore = nilaipertama.getText().toString();
        String secondscore = nilaikedua.getText().toString();

        if(firstscore.isEmpty() && secondscore.isEmpty()){
            Toast.makeText(this, "Nilai tidak boleh kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (firstscore.isEmpty()) {
            Toast.makeText(this, "Nilai pertama kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (secondscore.isEmpty()) {
            Toast.makeText(this, "Nilai kedua kosong !", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            int score1 = Integer.parseInt(firstscore);
            int score2 = Integer.parseInt(secondscore);
            int divide = round(score1 / score2);
            return divide;
        }
        catch (ArithmeticException e){
            Toast.makeText(this, "Tidak bisa membagi nol !", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    }

