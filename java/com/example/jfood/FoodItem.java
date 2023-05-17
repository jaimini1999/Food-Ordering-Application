package com.example.jfood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FoodItem extends AppCompatActivity {

    Button s2,button6;
    ImageView im2;
    EditText f1,f2,f3,f4,f5;
    final int REQUEST_CODE_GALLERY = 999;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_items);

        s2=findViewById(R.id.s2);
        button6 = findViewById(R.id.button6);
        f1=findViewById(R.id.f1);
        f2=findViewById(R.id.f2);
        f3=findViewById(R.id.f3);
        f4=findViewById(R.id.f4);
        f5=findViewById(R.id.f5);
        im2 = findViewById(R.id.imageView4);

        DB = new DBHelper(this);

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemid = f1.getText().toString();
                String name = f2.getText().toString();
                String description = f3.getText().toString();
                String price = f4.getText().toString();
                String categoryname = f5.getText().toString();


                if (itemid.equals("") || name.equals("") || description.equals("") || price.equals("") || categoryname.equals(""))
                {
                    Toast.makeText(FoodItem.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean insert = DB.insertFoodItem(itemid,name,description,price,categoryname,imageViewToByte(im2));
                    if(insert==true)
                    {
                        Toast.makeText(FoodItem.this, "FoodItem is added successfully", Toast.LENGTH_SHORT).show();
                        f1.setText("");
                        f2.setText("");
                        f3.setText("");
                        f4.setText("");
                        f5.setText("");
                        im2.setImageResource(R.mipmap.ic_launcher);

                        Intent intent = new Intent(getApplicationContext(), FoodItemMain.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(FoodItem.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        FoodItem.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                im2.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
