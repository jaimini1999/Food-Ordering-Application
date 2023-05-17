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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.CategoryAdapter;
import com.example.jfood.models.CategoryModel;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    TextView tv;
    EditText edt1;
    Button hb, hb1,hb2,hb3,hb4,search;
    private ArrayList<CategoryModel> ar;
    private DBHelper db;
    private CatAdapterG ga;
    private GridView grid;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        tv = findViewById(R.id.tv);
        edt1 = findViewById(R.id.edt1);
        hb = findViewById(R.id.hb);
        hb1 = findViewById(R.id.hb1);
        hb2 = findViewById(R.id.hb2);
        hb3 = findViewById(R.id.hb3);
        hb4 = findViewById(R.id.hb4);
        search = findViewById(R.id.search);
        grid = findViewById(R.id.grid);


        session = new Session(Home.this);
        String uname = session.getuser();
        tv.setText("Welcome " + uname);

        ar = new ArrayList<>();
        db = new DBHelper(Home.this);

        ar = db.readCat();

        ar = db.readCat();

        ga = new CatAdapterG(Home.this,R.layout.category_data_show,ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(ga);


        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFoodItem();
                Toast.makeText(getApplicationContext(), "Food Item Page", Toast.LENGTH_SHORT).show();
            }
        });

        hb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMyProfile();
                Toast.makeText(getApplicationContext(), "My Profile Page", Toast.LENGTH_SHORT).show();
            }
        });

        hb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCartHistory();
                Toast.makeText(getApplicationContext(), "Cart History Page", Toast.LENGTH_SHORT).show();
            }
        });

        hb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWelcome();
                Toast.makeText(getApplicationContext(), "Welcome Page", Toast.LENGTH_SHORT).show();
            }
        });

        hb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFavorite();
                Toast.makeText(getApplicationContext(), "Favorite Page", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(Home.this);
                String s = edt1.getText().toString();
                ar = db.searchCat(s);

                ga = new CatAdapterG(Home.this,R.layout.category_data_show,ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(ga);

                }
            });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Food Items"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update

                            db = new DBHelper(Home.this);

                            Cursor c = db.dataReadCat();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogFoodItem(Home.this, arrID.get(position));

                        }
                        }
                });
                dialog.show();

            }
        });
    }

    private void showDialogFoodItem(Home home, Integer itemid) {


        Toast.makeText(getApplicationContext(), "Food Item"+itemid,Toast.LENGTH_SHORT).show();

        session = new Session(Home.this);
        session.setcid(itemid+"");


        Intent intent = new Intent(getApplicationContext(), HomeFoodGrid.class);
        startActivity(intent);
    }

    private void gotoMyProfile() {
        Intent intent = new Intent(Home.this, MyProfile.class);
        startActivity(intent);
    }

    private void gotoFoodItem() {
        Intent intent = new Intent(Home.this, FoodItemMain.class);
        startActivity(intent);
    }

    private void gotoCartHistory() {
        Intent intent = new Intent(Home.this, CartHistory.class);
        startActivity(intent);
    }

    private void gotoFavorite() {
        Intent intent = new Intent(Home.this, AddToFavorites.class);
        startActivity(intent);
    }

    private void gotoWelcome() {
        Intent intent = new Intent(Home.this, Welcome.class);
        startActivity(intent);
    }
    }


