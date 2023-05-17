package com.example.jfood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.CategoryAdapter;
import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.adapters.OrderAdapter;
import com.example.jfood.models.FoodItemModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class OrderMain extends AppCompatActivity {

    TextView tv;
    EditText edt13;
    Button search3;
    DBHelper DB;
    private ArrayList<OrderModel> ar;
    private DBHelper db;
    private OrderAdapter oa;
    private RecyclerView rv;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_main);

        tv = findViewById(R.id.tv);
        edt13 = findViewById(R.id.edt13);
        search3 = findViewById(R.id.search3);

        Session session=new Session(OrderMain.this);
        String uname=session.getuser();
        tv.setText("Welcome "+uname);

        ar = new ArrayList<>();
        db = new DBHelper(OrderMain.this);

        ar = db.readOrder();

        oa = new OrderAdapter(ar, OrderMain.this);
        rv = findViewById(R.id.rec);

        linearLayoutManager = new LinearLayoutManager(OrderMain.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);


        rv.setAdapter(oa);

        search3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(OrderMain.this);
                String s = edt13.getText().toString();
                ar = db.searchOrder(s);

                oa = new OrderAdapter(ar, OrderMain.this);
                rv = findViewById(R.id.rec);

                linearLayoutManager = new LinearLayoutManager(OrderMain.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);


                rv.setAdapter(oa);

            }
        });
    }
}
