package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    Button a3;
    EditText a1, a2;
    DBHelper DB;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

         a3 = findViewById(R.id.a3);
         a1 = findViewById(R.id.a1);
         a2 = findViewById(R.id.a2);

        DB = new DBHelper(this);

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = a1.getText().toString();
                String pass = a2.getText().toString();

                if (email.equals("") || pass.equals(""))
                    Toast.makeText(AdminLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else if (email.equals("Jaimini@gmail.com") || pass.equals("Jaimini123")) {

                    session = new Session(AdminLogin.this);
                    session.setuser(a1.getText().toString());

                    Toast.makeText(AdminLogin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminFunction.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
