package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminFunction extends AppCompatActivity {

    Button f1,f2,f3,f4,f5,f6,f7;
    TextView tv1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_functions);

        f1=findViewById(R.id.f1);
        f2=findViewById(R.id.f2);
        f3=findViewById(R.id.f3);
        f4=findViewById(R.id.f4);
        f5=findViewById(R.id.f5);
        f6=findViewById(R.id.f6);
        f7=findViewById(R.id.f7);


        TextView tv1=findViewById(R.id.tv1);

        Session session=new Session(AdminFunction.this);
        String uname=session.getuser();
        tv1.setText("Welcome "+uname);

        DB = new DBHelper(this);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreatFood();
                Toast.makeText(getApplicationContext(),"Create Food Page", Toast.LENGTH_SHORT).show();
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoShowFood();
                Toast.makeText(getApplicationContext(),"Show Food Page", Toast.LENGTH_SHORT).show();
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManageFood();
                Toast.makeText(getApplicationContext(),"Manage Food Page", Toast.LENGTH_SHORT).show();
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoShowUsers();
                Toast.makeText(getApplicationContext(),"Show Users Page", Toast.LENGTH_SHORT).show();
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManageOrder();
                Toast.makeText(getApplicationContext(),"Manage Order Page", Toast.LENGTH_SHORT).show();
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoManageUsers();
                Toast.makeText(getApplicationContext(),"Manage Users Page", Toast.LENGTH_SHORT).show();
            }
        });
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistration();
                Toast.makeText(getApplicationContext(),"Registration Page", Toast.LENGTH_SHORT).show();
            }
        });
}

    private void gotoRegistration() {
        Intent intent = new Intent(AdminFunction.this,RegistrationActivity.class);
        startActivity(intent);
    }

    private void gotoManageUsers() {
        Intent intent = new Intent(AdminFunction.this,ManageUsers.class);
        startActivity(intent);
    }

    private void gotoManageOrder() {
        Intent intent = new Intent(AdminFunction.this,ManageOrder.class);
        startActivity(intent);
    }

    private void gotoShowUsers() {
        Intent intent = new Intent(AdminFunction.this,MainActivity.class);
        startActivity(intent);
    }

    private void gotoManageFood() {
        Intent intent = new Intent(AdminFunction.this,ManageFood.class);
        startActivity(intent);
    }

    private void gotoShowFood() {
        Intent intent = new Intent(AdminFunction.this,ShowFood.class);
        startActivity(intent);
    }

    private void gotoCreatFood() {
        Intent intent = new Intent(AdminFunction.this,LoginSecond.class);
        startActivity(intent);
    }
}
