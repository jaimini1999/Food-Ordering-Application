package com.example.jfood;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.FoodAdapterG;
import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;


public class HomeFoodGrid extends AppCompatActivity {

    TextView tv;
    EditText edt12;
    Button search2;
    String itemid,categoryname,cid,cname;
    Session session;

    DBHelper DB;
    private ArrayList<CategoryModel> ar1;
    private ArrayList<FoodItemModel> ar;
    private DBHelper db;
    private FoodAdapterG fa;
    private GridView grid;
    LinearLayoutManager linearLayoutManager;
String fid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_food_grid);

        tv = findViewById(R.id.tv);
        edt12 = findViewById(R.id.edt12);
        search2 = findViewById(R.id.search2);
        grid = findViewById(R.id.grid);

        session = new Session(HomeFoodGrid.this);
        String uname = session.getuser();
        tv.setText("Welcome " + uname);

        cid= session.getcid();


        ar = new ArrayList<>();

        ar1= new ArrayList<>();
        db = new DBHelper(HomeFoodGrid.this);

        Cursor cr =db.readfood2(cid);
        if (cr.moveToFirst()) {
            do {

                ar1.add(new CategoryModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getBlob(2)));
                cname=cr.getString(1);

            } while (cr.moveToNext());

        }
        cr.close();
        session.setcat(cname);
        ar = db.readFoodc2(cname);

        fa = new FoodAdapterG(HomeFoodGrid.this,R.layout.food_data_show,ar);
        grid = findViewById(R.id.grid);
        grid.setAdapter(fa);



        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(HomeFoodGrid.this);
                String s = edt12.getText().toString();
                ar = db.searchFood(s);

                fa = new FoodAdapterG(HomeFoodGrid.this,R.layout.food_data_show,ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(fa);

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Add to cart", "Add to favorites"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(HomeFoodGrid.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // addtocart

                            db = new DBHelper(HomeFoodGrid.this);

                            Cursor c = db.dataReadFood();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogAddtocart(HomeFoodGrid.this, arrID.get(position));

                        } else {
                            // addtofavorite
                            Cursor c = db.dataReadFood();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogAddtofavorites(arrID.get(position));
                        }
                    }
                });
                dialog.show();

            }
        });

    }

    private void showDialogAddtofavorites(Integer itemid) {

        session=new Session(HomeFoodGrid.this);
        session.setid(""+itemid);


        Toast.makeText(getApplicationContext(), "add to favorites"+itemid,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), AddToFavorites.class);
        startActivity(intent);
    }

    private void showDialogAddtocart(HomeFoodGrid homeFoodGrid, Integer itemid) {

         session=new Session(HomeFoodGrid.this);
        session.setid(""+itemid);

        Toast.makeText(getApplicationContext(), "add to cart"+itemid,Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getApplicationContext(), AddToCart.class);
        startActivity(intent);
    }

    private void updateFoodList(){
        // get all data from sqlite

        ar = new ArrayList<>();
        db = new DBHelper(HomeFoodGrid.this);

        ar = db.readFood();

        fa = new FoodAdapterG(HomeFoodGrid.this,R.layout.food_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(fa);
    }
}
