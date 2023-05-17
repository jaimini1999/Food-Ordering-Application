package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button b5,b6;
    EditText edt4,edt5;
    DBHelper DB;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

         b5=findViewById(R.id.btn5);
         b6=findViewById(R.id.btn6);
         edt4=findViewById(R.id.edt4);
         edt5=findViewById(R.id.edt5);

        DB=new DBHelper(this);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt4.getText().toString();
                String pass = edt5.getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkemailpassword(email, pass);
                    if(checkuserpass==true){

                        session = new Session(Login.this);
                        session.setuser(edt4.getText().toString());

                        Toast.makeText(Login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistration();
                Toast.makeText(getApplicationContext(),"Registration Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoRegistration() {
        Intent intent = new Intent(Login.this,RegistrationActivity.class);
        startActivity(intent);

    }
}
