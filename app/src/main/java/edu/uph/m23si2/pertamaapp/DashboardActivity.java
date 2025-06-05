package edu.uph.m23si2.pertamaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout llyProfil,llyTodolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        llyProfil= findViewById(R.id.llyProfil);
        llyTodolist=findViewById(R.id.llyTodolist);
        llyProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfil();
            }
        });
        llyTodolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTodolist();
            }
        });
    }
    public void toProfil(){
        Intent intent = new Intent(this,ProfilActivity.class);
        intent.putExtra("nama","Satria");
        startActivity(intent);
    }

    public void toTodolist(){
        Intent intent = new Intent(this,TodoListActivity.class);
        startActivity(intent);
    }

}