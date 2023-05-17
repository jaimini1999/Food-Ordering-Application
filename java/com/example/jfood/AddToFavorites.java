package com.example.jfood;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.CatAdapterG;
import com.example.jfood.adapters.FavoriteAdapter;
import com.example.jfood.adapters.FoodAdapterG;
import com.example.jfood.adapters.FoodItemAdapter;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FavoriteModel;
import com.example.jfood.models.FoodItemModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddToFavorites extends AppCompatActivity {


    private ArrayList<FavoriteModel> ar;
    private ArrayList<FoodItemModel> ar2;
    private DBHelper db;
    private FavoriteAdapter fa;
    private RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    String itemid,email;
    String name;
    String description;
    String price;
    String categoryname;
    ImageView im2;

    byte[] image;
    DBHelper DB;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_favorites);

        rv = findViewById(R.id.rec);


        session = new Session(AddToFavorites.this);
        itemid = session.getid();
        email = session.getuser();


        ar = new ArrayList<>();
        ar2 = new ArrayList<>();
        db = new DBHelper(AddToFavorites.this);


        Toast.makeText(AddToFavorites.this, "id"+itemid+session.getcat(), Toast.LENGTH_SHORT).show();



        Cursor cr = db.readfooditem1Data(session.getcat()+itemid);
        if (cr.moveToFirst()) {
            do {
                ar2.add(new FoodItemModel(

                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
                itemid=cr.getString(0);
                name=cr.getString(1);
                description=cr.getString(2);
                price=cr.getString(3);
                categoryname=cr.getString(4);
                image=cr.getBlob(5);


            } while (cr.moveToNext());

        }
        cr.close();



        if (itemid.equals("") || email.equals("") || name.equals("") || description.equals("") || price.equals("") || categoryname.equals(""))
        {
            Toast.makeText(AddToFavorites.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            DB=new DBHelper(AddToFavorites.this);
            Boolean insert = DB.insertfavorite(itemid, email, name, description, price, categoryname, image);
            if (insert == true) {

                Toast.makeText(AddToFavorites.this, "Added To Favorites", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), AddToFavorites.class);
                startActivity(intent);
            } else {
                Toast.makeText(AddToFavorites.this, "failed", Toast.LENGTH_SHORT).show();
            }


            Toast.makeText(AddToFavorites.this, "id" + itemid + session.getcat(), Toast.LENGTH_SHORT).show();

            ar = new ArrayList<>();
            db = new DBHelper(AddToFavorites.this);

            ar = db.readfavorite(email);

            fa = new FavoriteAdapter(ar, AddToFavorites.this);
            rv = findViewById(R.id.rec);

            linearLayoutManager = new LinearLayoutManager(AddToFavorites.this, RecyclerView.VERTICAL, false);
            rv.setLayoutManager(linearLayoutManager);


            rv.setAdapter(fa);


        }
    }

            public static byte[] imageViewToByte (ImageView image){
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        }


