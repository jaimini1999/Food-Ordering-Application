package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class MyProfile extends AppCompatActivity {

    TextView textm,mt1,mt2,mt3,mt4,mt5;
    Button mbtn;
    DBHelper DB;
    private ArrayList<MainModel> ar;
    private DBHelper db;
    private MainAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        textm = findViewById(R.id.textm);
        mt1 = findViewById(R.id.mt1);
        mt2 = findViewById(R.id.mt2);
        mt3 = findViewById(R.id.mt3);
        mt4 = findViewById(R.id.mt4);
        mt5 = findViewById(R.id.mt5);
        mbtn = findViewById(R.id.mbtn);


        DB = new DBHelper(this);

        Session session=new Session(MyProfile.this);
        String uname=session.getuser();
        textm.setText("Welcome "+uname);

        ar = new ArrayList<>();
        db = new DBHelper(MyProfile.this);


        Cursor cr = db.readOneData(uname);
        if (cr.moveToFirst()) {
            do {

                mt1.setText(cr.getString(1));
                mt2.setText(cr.getString(2));
                mt3.setText(cr.getString(3));
                mt4.setText(cr.getString(4));
                mt5.setText(cr.getString(5));

            } while (cr.moveToNext());

        }
        cr.close();


        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUpdate();
                Toast.makeText(getApplicationContext(),"Update Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoUpdate() {
        Intent intent = new Intent(MyProfile.this,UpdateProfile.class);
        startActivity(intent);
    }
}
