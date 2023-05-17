package com.example.jfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.CategoryAdapter;
import com.example.jfood.models.CategoryModel;
import android.widget.ListAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import android.widget.AdapterView;

public class ManageFood extends AppCompatActivity {


    EditText edt16;
    Button search6,hb;
    private ArrayList<CategoryModel> ar;
    private DBHelper db;
    private CatAdapterG ga;
    private GridView grid;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_food);

        edt16 = findViewById(R.id.edt16);
        search6 = findViewById(R.id.search6);
        hb = findViewById(R.id.hb);
        grid = findViewById(R.id.grid);

        ar = new ArrayList<>();
        db = new DBHelper(ManageFood.this);

        ar = db.readCat();

        ga = new CatAdapterG(ManageFood.this,R.layout.category_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(ga);


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
                db = new DBHelper(ManageFood.this);
                String s = edt16.getText().toString();
                ar = db.searchCat(s);

                ga = new CatAdapterG(ManageFood.this,R.layout.category_data_show,ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(ga);

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageFood.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update

                            db = new DBHelper(ManageFood.this);

                            Cursor c = db.dataReadCat();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ManageFood.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.dataReadCat();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
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

    private void showDialogUpdate(ManageFood manageFood, Integer Catid) {

        Session session=new Session(ManageFood.this);
        session.setid(""+Catid);

        Toast.makeText(getApplicationContext(), "update"+Catid,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), EditCat.class);
        startActivity(intent);

    }

    private void showDialogDelete(int Catid)
    {

        db = new DBHelper(ManageFood.this);
        db.deleteCatData(Catid);
        Toast.makeText(getApplicationContext(), "delete"+Catid,Toast.LENGTH_SHORT).show();

        updateFoodList();
    }

    private void gotoFoodItem() {
        Intent intent = new Intent(ManageFood.this, FoodGridView.class);
        startActivity(intent);
    }
    private void updateFoodList(){
        // get all data from sqlite

        ar = new ArrayList<>();
        db = new DBHelper(ManageFood.this);

        ar = db.readCat();

        ga = new CatAdapterG(ManageFood.this,R.layout.category_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(ga);
    }
}
