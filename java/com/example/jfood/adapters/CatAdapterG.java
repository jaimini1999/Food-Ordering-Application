package com.example.jfood.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jfood.ManageFood;
import com.example.jfood.R;
import com.example.jfood.models.CategoryModel;

import java.util.ArrayList;

public class CatAdapterG extends BaseAdapter {

    private ArrayList<CategoryModel> ar;
    private Context context;
    private  int layout;
    private ArrayList<CategoryModel> foodsList;

    public CatAdapterG(Context context, int layout, ArrayList<CategoryModel> foodsList) {
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
        private TextView id,categoryname;
        private ImageView imageView1;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.id = (TextView) row.findViewById(R.id.id);
            holder.categoryname = (TextView) row.findViewById(R.id.categoryname);
            holder.imageView1 = (ImageView) row.findViewById(R.id.imageView1);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        CategoryModel food = foodsList.get(position);

        holder.id.setText(food.getId());
        holder.categoryname.setText(food.getCategoryname());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView1.setImageBitmap(bitmap);

        return row;
    }
}
