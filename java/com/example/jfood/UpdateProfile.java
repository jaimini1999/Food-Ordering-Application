package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class UpdateProfile extends AppCompatActivity {

    EditText uet1,uet2,uet3,uet4,uet5;
    Button ubtn;
    DBHelper DB;
    private ArrayList<MainModel> ar;
    private DBHelper db;
    private MainAdapter ma;
      String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        Session session=new Session(UpdateProfile.this);
         uname=session.getuser();

        uet1 = findViewById(R.id.uet1);
        uet2 = findViewById(R.id.uet2);
        uet3 = findViewById(R.id.uet3);
        uet4 = findViewById(R.id.uet4);
        uet5 = findViewById(R.id.uet5);
        ubtn = findViewById(R.id.ubtn);

        DB = new DBHelper(this);

        ar = new ArrayList<>();
        db = new DBHelper(UpdateProfile.this);

        Cursor cr = db.readOneData(uname);
        if (cr.moveToFirst()) {
            do {


                uet1.setText(cr.getString(1));
                uet2.setText(cr.getString(2));
                uet3.setText(cr.getString(3));
                uet4.setText(cr.getString(4));
                uet5.setText(cr.getString(5));

            } while (cr.moveToNext());

        }
        cr.close();


        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateOneData(uet1.getText().toString(),uet2.getText().toString(),uet4.getText().toString(),uet5.getText().toString());
                Toast.makeText(UpdateProfile.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                startActivity(intent);
                    }
        });

    }
}

