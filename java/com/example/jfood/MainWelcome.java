package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainWelcome extends AppCompatActivity {

    Button m1,m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_welcome);

        Button m1=findViewById(R.id.m1);
        Button m2=findViewById(R.id.m2);

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAdmin();
                Toast.makeText(getApplicationContext(),"Admin Page",Toast.LENGTH_SHORT).show();
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUser();
                Toast.makeText(getApplicationContext(),"User Page",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void gotoAdmin() {
        Intent intent = new Intent(MainWelcome.this,AdminLogin.class);
        startActivity(intent);

    }
    private void gotoUser() {
        Intent intent = new Intent(MainWelcome.this,Welcome.class);
        startActivity(intent);
    }
}
