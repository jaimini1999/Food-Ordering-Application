package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.location.LocationRequestCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.FoodAdapterG;
import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class AddToCart extends AppCompatActivity {

    EditText deliveryaddress,quantity,total;
    TextView price;
    Button add,minus,order;
    String itemid,fid;
    Integer a;
    Integer p,q,ans;
    Session session;
    String email;

    DBHelper DB;
    private ArrayList<FoodItemModel> ar;
    private DBHelper db;
    private FoodAdapterG fa;
    private GridView grid;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_cart);

        deliveryaddress = findViewById(R.id.address);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        order = findViewById(R.id.order);
        total = findViewById(R.id.total);

        session = new Session(AddToCart.this);
        itemid = session.getid();
        email= session.getuser();

        ar = new ArrayList<>();
        db = new DBHelper(AddToCart.this);

        ar = db.readFooditem(session.getcat()+itemid);

        fa = new FoodAdapterG(AddToCart.this,R.layout.food_data_show,ar);
        grid = findViewById(R.id.grid);
        grid.setAdapter(fa);


        Toast.makeText(AddToCart.this, "id"+itemid+session.getcat(), Toast.LENGTH_SHORT).show();



        Cursor cr = db.readfooditem1Data(session.getcat()+itemid);
        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(

                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
                price.setText(cr.getString(3));
                total.setText(cr.getString(3));

            } while (cr.moveToNext());

        }
        cr.close();

        grid.setAdapter(fa);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a=Integer.parseInt(quantity.getText().toString());
                a=a+1;
                quantity.setText(a+"");
                p=Integer.parseInt(quantity.getText().toString());
                q=Integer.parseInt(price.getText().toString());
                ans=p*q;
                total.setText(ans+"");


            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a=Integer.parseInt(quantity.getText().toString());
                if(a==1)
                {quantity.setText(a+"");}
                else
                {a=a-1;
                quantity.setText(a+"");}
                p=Integer.parseInt(quantity.getText().toString());
                q=Integer.parseInt(price.getText().toString());
                ans=p*q;
                total.setText(ans+"");
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p2 = price.getText().toString();
                String q2 = quantity.getText().toString();
                String d2 = deliveryaddress.getText().toString();
                String t2 = total.getText().toString();

                Toast.makeText(AddToCart.this, " "+p2+q2+d2+t2+email+itemid, Toast.LENGTH_SHORT).show();

                if (email.equals("") || itemid.equals("") || p2.equals("") || q2.equals("") || d2.equals("") || t2.equals(""))
                {
                    Toast.makeText(AddToCart.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DB=new DBHelper(AddToCart.this);
                    Boolean insert = DB.insertOrders(itemid,email,p2,q2,d2,t2);
                    if(insert==true)
                    {
                        Toast.makeText(AddToCart.this, "FoodItem is placed successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CartHistory.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(AddToCart.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
