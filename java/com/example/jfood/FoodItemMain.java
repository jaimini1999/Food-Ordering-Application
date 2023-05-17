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
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class FoodItemMain extends AppCompatActivity {

    TextView tv;
    EditText edt12;
    Button search2;
    DBHelper DB;
    private ArrayList<FoodItemModel> ar;
    private DBHelper db;
    private FoodItemAdapter fa;
    private RecyclerView rv;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_items_main);

        tv = findViewById(R.id.tv);
        edt12 = findViewById(R.id.edt12);
        search2 = findViewById(R.id.search2);

        Session session=new Session(FoodItemMain.this);
        String uname=session.getuser();
        tv.setText("Welcome "+uname);

        ar = new ArrayList<>();
        db = new DBHelper(FoodItemMain.this);

        ar = db.readFood();

        fa = new FoodItemAdapter(ar, FoodItemMain.this);
        rv = findViewById(R.id.rec);

        linearLayoutManager = new LinearLayoutManager(FoodItemMain.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);


        rv.setAdapter(fa);


        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(FoodItemMain.this);
                String s = edt12.getText().toString();
                ar = db.searchFood(s);

                fa = new FoodItemAdapter(ar, FoodItemMain.this);
                rv = findViewById(R.id.rec);

                linearLayoutManager = new LinearLayoutManager(FoodItemMain.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);


                rv.setAdapter(fa);

            }
        });
    }
}
