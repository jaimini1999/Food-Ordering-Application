package com.example.jfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText edt14;
    Button search4;
    DBHelper DB;
    private ArrayList<MainModel> ar;
    private DBHelper db;
    private MainAdapter ma;
    private RecyclerView rv;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv = findViewById(R.id.tv);
        edt14 = findViewById(R.id.edt14);
        search4 = findViewById(R.id.search4);

        Session session=new Session(MainActivity.this);
        String uname=session.getuser();
        tv.setText("Welcome "+uname);

        ar = new ArrayList<>();
        db = new DBHelper(MainActivity.this);

        ar = db.readData();

        ma = new MainAdapter(ar, MainActivity.this);
        rv = findViewById(R.id.rec);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);


        rv.setAdapter(ma);

        search4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(MainActivity.this);
                String s = edt14.getText().toString();
                ar = db.searchData(s);

                ma = new MainAdapter(ar, MainActivity.this);
                rv = findViewById(R.id.rec);

                linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);


                rv.setAdapter(ma);

            }
        });
    }
    }