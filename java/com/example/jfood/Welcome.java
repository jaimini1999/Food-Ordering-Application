package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

         btn1=findViewById(R.id.btn1);
         btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistration();
                Toast.makeText(getApplicationContext(),"Registration Page", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
                Toast.makeText(getApplicationContext(),"Login Page",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void gotoRegistration() {
        Intent intent = new Intent(Welcome.this,RegistrationActivity.class);
        startActivity(intent);

    }
    private void gotoLogin() {
        Intent intent = new Intent(Welcome.this,Login.class);
        startActivity(intent);
    }
}
