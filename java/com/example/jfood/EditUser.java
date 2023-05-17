package com.example.jfood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.adapters.UserAdapterG;
import com.example.jfood.models.MainModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class EditUser extends AppCompatActivity {


    EditText u1,u2,u3,uid;
    Button u6;
    DBHelper DB;
    private ArrayList<MainModel> ar;
    private DBHelper db;
    private UserAdapterG ma;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        uid = findViewById(R.id.uid);
        u1 = findViewById(R.id.u1);
        u2 = findViewById(R.id.u2);
        u3 = findViewById(R.id.u3);
        u6 = findViewById(R.id.u6);

        Session session=new Session(EditUser.this);
        userid=session.getid();

        DB = new DBHelper(EditUser.this);

        ar = new ArrayList<>();
        db = new DBHelper(EditUser.this);

        Cursor cr = db.readuser1Data(userid);
        if (cr.moveToFirst()) {
            do {

                uid.setText(cr.getString(0));
                u1.setText(cr.getString(2));
                u2.setText(cr.getString(4));
                u3.setText(cr.getString(5));

            } while (cr.moveToNext());

        }
        cr.close();


        u6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateuser1Data(uid.getText().toString(),u1.getText().toString(),u2.getText().toString(),u3.getText().toString());
                Toast.makeText(EditUser.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ManageUsers.class);
                startActivity(intent);
            }
        });
    }
}
