package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    Button s3;
    EditText o1,o2,o3,o4,o5,o6,o7;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        s3=findViewById(R.id.s3);
        o1=findViewById(R.id.o1);
        o2=findViewById(R.id.o2);
        o3=findViewById(R.id.o3);
        o4=findViewById(R.id.o4);
        o5=findViewById(R.id.o5);
        o6=findViewById(R.id.o6);
        o7=findViewById(R.id.o7);

        DB = new DBHelper(this);

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemid = o2.getText().toString();
                String email = o3.getText().toString();
                String price = o4.getText().toString();
                String quantity = o5.getText().toString();
                String deliveryaddress = o6.getText().toString();
                String total = o7.getText().toString();

                if ( itemid.equals("") || email.equals("") || price.equals("") || quantity.equals("") || deliveryaddress.equals("") || total.equals(""))
                {
                    Toast.makeText(OrderActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean insert = DB.insertOrders(itemid,email,price,quantity,deliveryaddress,total);
                    if(insert==true)
                    {
                        Toast.makeText(OrderActivity.this, "FoodItem is added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),OrderMain.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(OrderActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
