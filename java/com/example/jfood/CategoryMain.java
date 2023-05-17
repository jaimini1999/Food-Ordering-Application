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
import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.CategoryModel;

import java.util.ArrayList;

public class CategoryMain extends AppCompatActivity {

    TextView tv;
    EditText edt11;
    Button search1;
    DBHelper DB;
    private ArrayList<CategoryModel> ar;
    private DBHelper db;
    private CategoryAdapter ca;
    private RecyclerView rv;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_main);

        tv = findViewById(R.id.tv);
        edt11 = findViewById(R.id.edt11);
        search1 = findViewById(R.id.search1);

        Session session=new Session(CategoryMain.this);
        String uname=session.getuser();
        tv.setText("Welcome "+uname);

        ar = new ArrayList<>();
        db = new DBHelper(CategoryMain.this);

        ar = db.readCat();

        ca = new CategoryAdapter(ar, CategoryMain.this);
        rv = findViewById(R.id.rec);

        linearLayoutManager = new LinearLayoutManager(CategoryMain.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);


        rv.setAdapter(ca);

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(CategoryMain.this);
                String s = edt11.getText().toString();
                ar = db.searchCat(s);

                ca = new CategoryAdapter(ar, CategoryMain.this);
                rv = findViewById(R.id.rec);

                linearLayoutManager = new LinearLayoutManager(CategoryMain.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);


                rv.setAdapter(ca);

            }
        });
    }
}
