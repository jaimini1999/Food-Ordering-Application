package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSecond extends AppCompatActivity {

    Button l1,l2,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_second);

         l1=findViewById(R.id.l1);
         l2=findViewById(R.id.l2);
         l3=findViewById(R.id.l3);


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCategory();
                Toast.makeText(getApplicationContext(),"Category Page",Toast.LENGTH_SHORT).show();
            }
        });

       l2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               gotoFoodItem();
               Toast.makeText(getApplicationContext(),"FoodItem Page",Toast.LENGTH_SHORT).show();
           }
       });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoOrders();
                Toast.makeText(getApplicationContext(),"Orders Page",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void gotoFoodItem() {
        Intent intent = new Intent(LoginSecond.this,FoodItem.class);
        startActivity(intent);
    }

    private void gotoOrders() {
        Intent intent = new Intent(LoginSecond.this,OrderActivity.class);
        startActivity(intent);
    }



    private void gotoCategory() {
        Intent intent = new Intent(LoginSecond.this,Category.class);
        startActivity(intent);

    }
}
