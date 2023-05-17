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

import com.example.jfood.adapters.FoodAdapterG;
import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.models.FoodItemModel;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class EditFood extends AppCompatActivity {

    EditText fe1,fe2,fe3,fe4,feid;
    Button fe7;
    DBHelper DB;
    private ArrayList<FoodItemModel> ar;
    private DBHelper db;
    private FoodAdapterG fa;
    String itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_food);

        fe1 = findViewById(R.id.fe1);
        fe2 = findViewById(R.id.fe2);
        fe3 = findViewById(R.id.fe3);
        fe4 = findViewById(R.id.fe4);
        fe7 = findViewById(R.id.fe7);
        feid = findViewById(R.id.feid);

        Session session=new Session(EditFood.this);
        itemid=session.getid();

        Toast.makeText(EditFood.this, "id"+itemid, Toast.LENGTH_SHORT).show();

        DB = new DBHelper(EditFood.this);

        ar = new ArrayList<>();
        db = new DBHelper(EditFood.this);

        Cursor cr = db.readfood1Data(itemid);
        if (cr.moveToFirst()) {
            do {


                feid.setText(cr.getString(0));
                fe1.setText(cr.getString(1));
                fe2.setText(cr.getString(2));
                fe3.setText(cr.getString(3));
                fe4.setText(cr.getString(4));

            } while (cr.moveToNext());

        }
        cr.close();



        fe7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updatefood1Data(feid.getText().toString(),fe1.getText().toString(),fe2.getText().toString(),fe3.getText().toString(),fe4.getText().toString());
                Toast.makeText(EditFood.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FoodGridView.class);
                startActivity(intent);
            }
        });
    }
}
