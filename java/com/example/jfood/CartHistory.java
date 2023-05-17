package com.example.jfood;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jfood.adapters.OrderAdapterG;
import com.example.jfood.models.FoodItemModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class CartHistory extends AppCompatActivity {

    Session session;
    String itemid,email;
    private ArrayList<OrderModel> ar;
    private DBHelper db;
    private OrderAdapterG oa;
    private GridView grid;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_history);

        grid = findViewById(R.id.grid);

        Session session = new Session(CartHistory.this);
        itemid = session.getid();
        email = session.getuser();

        Toast.makeText(CartHistory.this, "id"+itemid, Toast.LENGTH_SHORT).show();
        ar = new ArrayList<>();
        db = new DBHelper(CartHistory.this);


        oa = new OrderAdapterG(CartHistory.this, R.layout.order_data_show, ar);
        grid = findViewById(R.id.grid);

        Cursor cr = db.readorder3(email);
        if (cr.moveToFirst()) {
            do {
                ar.add(new OrderModel(

                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6)));
            } while (cr.moveToNext());

        }
        cr.close();

        grid.setAdapter(oa);


    }
}
