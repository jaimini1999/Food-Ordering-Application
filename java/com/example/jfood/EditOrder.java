package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.OrderAdapter;
import com.example.jfood.adapters.OrderAdapterG;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class EditOrder extends AppCompatActivity {

    EditText o1,o2,o3,o4,oid;
    Button o5;

    DBHelper DB;
    private ArrayList<OrderModel> ar;
    private DBHelper db;
    private OrderAdapter oa;
    String orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);

        Session session=new Session(EditOrder.this);
        orderid=session.getid();

        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
        o5 = findViewById(R.id.o5);
        oid = findViewById(R.id.oid);

        DB = new DBHelper(this);

        ar = new ArrayList<>();
        db = new DBHelper(EditOrder.this);

        Cursor cr = db.readorder1Data(orderid);
        if (cr.moveToFirst()) {
            do {

                oid.setText(cr.getString(0));
                o1.setText(cr.getString(3));
                o2.setText(cr.getString(4));
                o3.setText(cr.getString(5));
                o4.setText(cr.getString(6));

            } while (cr.moveToNext());

        }
        cr.close();

        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateorderData(oid.getText().toString(),o1.getText().toString(),o2.getText().toString(),o3.getText().toString(),o4.getText().toString());
                Toast.makeText(EditOrder.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ManageOrder.class);
                startActivity(intent);
            }
        });
    }
}
