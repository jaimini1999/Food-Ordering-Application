package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class EditCat extends AppCompatActivity {

    EditText ecat1,ecatid;
    Button c2;
    DBHelper DB;
    private ArrayList<CategoryModel> ar;
    private DBHelper db;
    private CatAdapterG ca;
    String cid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cat);

        Session session=new Session(EditCat.this);
        cid=session.getid();

        ecat1 = findViewById(R.id.ecat1);
        ecatid = findViewById(R.id.ecatid);
        c2 = findViewById(R.id.c2);

        DB = new DBHelper(this);

        ar = new ArrayList<>();
        db = new DBHelper(EditCat.this);

        Cursor cr = db.readcat1Data(cid);
        if (cr.moveToFirst()) {
            do {


                ecatid.setText(cr.getString(0));
                ecat1.setText(cr.getString(1));

            } while (cr.moveToNext());

        }
        cr.close();

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.updatecatData(ecatid.getText().toString(),ecat1.getText().toString());
                Toast.makeText(EditCat.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ManageFood.class);
                startActivity(intent);
            }
        });


    }
}
