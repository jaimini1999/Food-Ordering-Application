package com.example.jfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.FoodAdapterG;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class FoodGridView extends AppCompatActivity {

    EditText edt16;
    Button search6;
    private ArrayList<FoodItemModel> ar;
    private DBHelper db;
    private FoodAdapterG fa;
    private GridView grid;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_gridview);

        edt16 = findViewById(R.id.edt16);
        search6 = findViewById(R.id.search6);
        grid = findViewById(R.id.grid);

        ar = new ArrayList<>();
        db = new DBHelper(FoodGridView.this);

        ar = db.readFood();

        fa = new FoodAdapterG(FoodGridView.this,R.layout.food_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(fa);

        search6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(FoodGridView.this);
                String s = edt16.getText().toString();
                ar = db.searchFood(s);

                fa = new FoodAdapterG(FoodGridView.this,R.layout.food_data_show,ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(fa);

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(FoodGridView.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update

                            db = new DBHelper(FoodGridView.this);

                            Cursor c = db.dataReadFood();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(FoodGridView.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.dataReadFood();
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

    private void showDialogDelete(Integer itemid) {

        db = new DBHelper(FoodGridView.this);
        db.deleteFoodData(itemid);
        Toast.makeText(getApplicationContext(), "delete"+itemid,Toast.LENGTH_SHORT).show();

        updateFoodList();

    }

    private void showDialogUpdate(FoodGridView foodGridView, Integer itemid) {
        Session session=new Session(FoodGridView.this);
        session.setid(""+itemid);

        Toast.makeText(getApplicationContext(), "update"+itemid,Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getApplicationContext(), EditFood.class);
        startActivity(intent);
    }

    private void updateFoodList(){
        // get all data from sqlite

        ar = new ArrayList<>();
        db = new DBHelper(FoodGridView.this);

        ar = db.readFood();

        fa = new FoodAdapterG(FoodGridView.this,R.layout.food_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(fa);
    }
}
