package com.example.jfood;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.MainAdapter;
import com.example.jfood.adapters.OrderAdapterG;
import com.example.jfood.adapters.UserAdapterG;
import com.example.jfood.models.MainModel;
import com.example.jfood.models.OrderModel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {

    EditText edt16,u1,u2,u3;
    TextView uid;
    Button search6,u6;
    private ArrayList<MainModel> ar;
    private DBHelper db;
    private UserAdapterG ua;
    private GridView grid;

    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_gridview);

        edt16 = findViewById(R.id.edt16);
        search6 = findViewById(R.id.search6);
        grid = findViewById(R.id.grid);


        ar = new ArrayList<>();
        db = new DBHelper(ManageUsers.this);

        ar = db.readData();

        ua = new UserAdapterG(ManageUsers.this, R.layout.main_data_show, ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(ua);


        search6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(ManageUsers.this);
                String s = edt16.getText().toString();
                ar = db.searchData(s);

                ua = new UserAdapterG(ManageUsers.this, R.layout.main_data_show, ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(ua);

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageUsers.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update

                            db = new DBHelper(ManageUsers.this);

                            Cursor c = db.dataReadUser();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ManageUsers.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.dataReadUser();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();

            }
        });
    }

    private void showDialogDelete(Integer userid) {

        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ManageUsers.this);
        db = new DBHelper(ManageUsers.this);
        db.deleteUserData(userid);
        Toast.makeText(getApplicationContext(), "delete" + userid, Toast.LENGTH_SHORT).show();

        updateFoodList();
    }



    private void showDialogUpdate(ManageUsers manageUsers, Integer userid) {

        Session session=new Session(ManageUsers.this);
        session.setid(""+userid);

        Toast.makeText(getApplicationContext(), "update" + userid, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), EditUser.class);
        startActivity(intent);
    }

    private void updateFoodList() {
        // get all data from sqlite

        ar = new ArrayList<>();
        db = new DBHelper(ManageUsers.this);

        ar = db.readData();

        ua = new UserAdapterG(ManageUsers.this, R.layout.main_data_show, ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(ua);
    }
}
