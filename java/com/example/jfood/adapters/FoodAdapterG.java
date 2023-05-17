package com.example.jfood.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jfood.R;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class FoodAdapterG extends BaseAdapter {

    private ArrayList<FoodItemModel> ar;
    private Context context;
    private  int layout;
    private ArrayList<FoodItemModel> foodsList;

    public FoodAdapterG(Context context, int layout, ArrayList<FoodItemModel> foodsList) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        private TextView itemid,fname,fdescription,fprice,fcname;
        private ImageView imageView2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.itemid = (TextView) row.findViewById(R.id.itemid);
            holder.fname = (TextView) row.findViewById(R.id.fname);
            holder.fdescription =  (TextView) row.findViewById(R.id.fdescription);
            holder.fprice =  (TextView) row.findViewById(R.id.fprice);
            holder.fcname =  (TextView) row.findViewById(R.id.fcname);
            holder.imageView2 = (ImageView) row.findViewById(R.id.imageView2);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        FoodItemModel food = foodsList.get(position);

        holder.itemid.setText(food.getItemid());
        holder.fname.setText(food.getFname());
        holder.fdescription.setText(food.getFdescription());
        holder.fprice.setText(food.getFprice());
        holder.fcname.setText(food.getFcname());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView2.setImageBitmap(bitmap);

        return row;
    }
}
