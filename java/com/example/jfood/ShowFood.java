package com.example.jfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.CategoryAdapter;
import com.example.jfood.models.CategoryModel;

import java.util.ArrayList;

public class ShowFood extends AppCompatActivity {

    Button search6,hb;
    EditText edt16;
    private ArrayList<CategoryModel> ar;
    private DBHelper db;
    private CategoryAdapter ca;
    private RecyclerView rv;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food);

        hb = findViewById(R.id.hb);
        edt16 = findViewById(R.id.edt16);
        search6 = findViewById(R.id.search6);

        ar = new ArrayList<>();
        db = new DBHelper(ShowFood.this);

        ar = db.readCat();

        ca = new CategoryAdapter(ar, ShowFood.this);
        rv = findViewById(R.id.rec);

        linearLayoutManager = new LinearLayoutManager(ShowFood.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);


        rv.setAdapter(ca);

        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFoodItem();
                Toast.makeText(getApplicationContext(), "Food Item Page", Toast.LENGTH_SHORT).show();
            }
        });

        search6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(ShowFood.this);
                String s = edt16.getText().toString();
                ar = db.searchCat(s);

                ca = new CategoryAdapter(ar, ShowFood.this);
                rv = findViewById(R.id.rec);

                linearLayoutManager = new LinearLayoutManager(ShowFood.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);


                rv.setAdapter(ca);

            }
        });
    }

    private void gotoFoodItem() {
        Intent intent = new Intent(ShowFood.this, FoodItemMain.class);
        startActivity(intent);
    }
}
