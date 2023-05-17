package com.example.jfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FavoriteModel;
import com.example.jfood.models.FoodItemModel;
import com.example.jfood.models.MainModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "FOSystem.db";

    public DBHelper(Context context) {

        super(context, "FOSystem.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users( userid TEXT primary key,email TEXT,name TEXT,password TEXT,mobile TEXT,address TEXT,cpass TEXT,image BLOB)");
        MyDB.execSQL("create Table categories( cid TEXT primary key,cname TEXT,image BLOB)");
        MyDB.execSQL("create Table foodItems( itemid TEXT primary key,name TEXT,description TEXT,price TEXT,categoryname TEXT,image BLOB)");
        MyDB.execSQL("create Table orders(orderid INTEGER primary key autoincrement,itemid TEXT,email TEXT,price TEXT,quantity TEXT,deliveryaddress TEXT,total TEXT)");
        MyDB.execSQL("create Table favorite( itemid TEXT primary key,email TEXT,name TEXT,description TEXT,price TEXT,categoryname TEXT,image BLOB)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists categories");
        MyDB.execSQL("drop Table if exists foodItems");
        MyDB.execSQL("drop Table if exists orders");
        MyDB.execSQL("drop Table if exists favorite");
    }

    public Boolean insertFoodItem(String  itemid, String name,String description,String price,String categoryname,byte[] image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("itemid", itemid);
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        cv.put("categoryname", categoryname);
        cv.put("image", image);
        long result = MyDB.insert("foodItems", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insertfavorite(String  itemid, String email, String name,String description,String price,String categoryname,byte[] image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("itemid", itemid);
        cv.put("email", email);
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        cv.put("categoryname", categoryname);
        cv.put("image", image);
        long result = MyDB.insert("favorite", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<FavoriteModel> readfavorite(String email)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  favorite where email = ?", new String[]{email});

        ArrayList<FavoriteModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FavoriteModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getBlob(6)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public Cursor readfavorite1(String itemid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  favorite where name = ?", new String[]{itemid});

        return cr;
    }

    public Boolean insertOrders( String itemid, String email, String price, String quantity, String deliveryaddress, String total) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("itemid", itemid);
        cv.put("email", email);
        cv.put("price", price);
        cv.put("quantity", quantity);
        cv.put("deliveryaddress", deliveryaddress);
        cv.put("total", total);
        long result = MyDB.insert("orders", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insertCat(String s1, String s2,byte[] image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cid", s1);
        cv.put("cname", s2);
        cv.put("image", image);
        long result = MyDB.insert("categories", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<OrderModel> readOrder()
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  orders", null);

        ArrayList<OrderModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new OrderModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public ArrayList<OrderModel> searchOrder(String s)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        s='%'+s+'%';
        Cursor cr = MyDB.rawQuery("SELECT * FROM  orders where email like '"+s+"'", null);

        ArrayList<OrderModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new OrderModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public ArrayList<CategoryModel> readCat()
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  categories", null);

        ArrayList<CategoryModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new CategoryModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getBlob(2)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
    public ArrayList<CategoryModel> searchCat(String s)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();
         s='%'+s+'%';
        Cursor cr = MyDB.rawQuery("SELECT * FROM  categories where cname like '"+s+"'", null);

        ArrayList<CategoryModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new CategoryModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getBlob(2)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public ArrayList<FoodItemModel> readFood()
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems", null);

        ArrayList<FoodItemModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
    public ArrayList<FoodItemModel> readFooditem(String item)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where name = ?", new String[]{item});

        ArrayList<FoodItemModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
    public ArrayList<FoodItemModel> readFoodc2(String cname)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where categoryname = ?", new String[]{cname});

        ArrayList<FoodItemModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
    public ArrayList<FoodItemModel> searchFood(String s)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        s='%'+s+'%';
        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where name like '"+s+"'", null);

        ArrayList<FoodItemModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public ArrayList<MainModel> readData()
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  users", null);

        ArrayList<MainModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {

                ar.add(new MainModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6),
                        cr.getBlob(7)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
    public ArrayList<MainModel> searchData(String s)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        s='%'+s+'%';
        Cursor cr = MyDB.rawQuery("SELECT * FROM  users where name like '"+s+"'", null);

        ArrayList<MainModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {

                ar.add(new MainModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5),
                        cr.getString(6),
                        cr.getBlob(7)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }

    public Cursor readOneData(String email)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  users where email = ?", new String[]{email});

        return cr;
    }


    public Boolean insertData(String userid,String email, String name, String password, String mobile, String address, String cpass,byte[] image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userid", userid);
        cv.put("email", email);
        cv.put("name", name);
        cv.put("password", password);
        cv.put("mobile", mobile);
        cv.put("address", address);
        cv.put("cpass", cpass);
        cv.put("image", image);
        long result = MyDB.insert("users", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Boolean updateOneData(String email, String name, String mobile, String address) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET name='"+name+"',mobile='"+mobile+"',address='"+address+"' where email='"+email+"'");
        return true;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkemailpassword(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and email = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor dataReadCat(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  categories", null);
        if (cursor.getCount() > 0);
        return MyDB.rawQuery("SELECT * FROM  categories", null);
    }

    public void deleteCatData(int id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("categories","cid="+id,null);
        MyDB.close();
    }

    public Cursor dataReadFood(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  foodItems", null);
        if (cursor.getCount() > 0);
        return MyDB.rawQuery("SELECT * FROM  foodItems", null);
    }

    public void deleteFoodData(int itemid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("foodItems","itemid="+itemid,null);
        MyDB.close();
    }

    public Cursor dataReadOrder(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  orders", null);
        if (cursor.getCount() > 0);
        return MyDB.rawQuery("SELECT * FROM  orders", null);
    }

    public void deleteOrderData(int orderid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("orders","orderid="+orderid,null);
        MyDB.close();
    }

    public Cursor dataReadUser(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  users", null);
        if (cursor.getCount() > 0);
        return MyDB.rawQuery("SELECT * FROM  users", null);
    }

    public void deleteUserData(int userid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("users","userid="+userid,null);
        MyDB.close();
    }

    public Boolean updateuserData(String userid, String name, String mobile, String address) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET name='"+name+"',mobile='"+mobile+"',address='"+address+"' where userid='"+userid+"'");
        return true;
    }


    public Boolean updatecatData(String cid, String cname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE categories SET cname='"+cname+"' where cid='"+cid+"'");
        return true;
    }

    public Cursor readcat1Data(String cid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  categories where cid = ?", new String[]{cid});

        return cr;
    }

    public Boolean updateorderData(String orderid, String price, String quantity, String deliveryaddress, String total) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE orders SET price='"+price+"',quantity='"+quantity+"',deliveryaddress='"+deliveryaddress+"',total='"+total+"' where orderid='"+orderid+"'");
        return true;
    }

    public Cursor readorder1Data(String orderid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  orders where orderid = ?", new String[]{orderid});

        return cr;
    }


    public Boolean updateuser1Data(String userid ,String name, String mobile ,String address ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET name='"+name+"',mobile='"+mobile+"',address='"+address+"' where userid='"+userid+"'");
        return true;
    }

    public Cursor readuser1Data(String userid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  users where userid = ?", new String[]{userid});

        return cr;
    }

    public Boolean updatefood1Data(String itemid ,String name ,String description ,String price ,String categoryname  ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE foodItems SET name='"+name+"',description='"+description+"',price='"+price+"',categoryname='"+categoryname+"' where itemid='"+itemid+"'");
        return true;
    }

    public Cursor readfooditem1Data(String itemid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where name = ?", new String[]{itemid});

        return cr;
    }

    public Cursor readfood1Data(String itemid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where itemid = ?", new String[]{itemid});

        return cr;
    }
    public Cursor readfood2(String cid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  categories where cid = ?", new String[]{cid});

        return cr;
    }

    public Cursor readfood2Data(String itemid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where itemid = ?", new String[]{itemid});

        return cr;
    }

    public Cursor readorder3(String email)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  orders where email = ?", new String[]{email});

        return cr;
    }

    public Cursor readfoodHome(String categoryname)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where categoryname = ?", new String[]{categoryname});

        return cr;
    }

    public Cursor readorder2Data(String itemid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  orders where itemid = ?", new String[]{itemid});

        return cr;
    }

    public ArrayList<FoodItemModel> readFoodi1(String fid)
    {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cr = MyDB.rawQuery("SELECT * FROM  foodItems where itemid = ?", new String[]{fid});

        ArrayList<FoodItemModel> ar = new ArrayList<>();

        if (cr.moveToFirst()) {
            do {
                ar.add(new FoodItemModel(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getBlob(5)));
            } while (cr.moveToNext());

        }
        cr.close();
        return ar;
    }
}
