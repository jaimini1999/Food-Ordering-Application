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
import com.example.jfood.models.MainModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class UserAdapterG extends BaseAdapter {

    private ArrayList<MainModel> ar;
    private Context context;
    private int layout;
    private ArrayList<MainModel> foodsList;

    public UserAdapterG(Context context, int layout, ArrayList<MainModel> foodsList) {
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

    private class ViewHolder {
        private TextView  userid,email,name,password,mobile,address,cpass;
        private ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.userid = (TextView) row.findViewById(R.id.userid);
            holder.email = (TextView) row.findViewById(R.id.email);
            holder.name = (TextView) row.findViewById(R.id.name);
            holder.password = (TextView) row.findViewById(R.id.password);
            holder.mobile = (TextView) row.findViewById(R.id.mobile);
            holder.address = (TextView) row.findViewById(R.id.address);
            holder.cpass = (TextView) row.findViewById(R.id.cpass);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        MainModel food = foodsList.get(position);

        holder.userid.setText(food.getUserid());
        holder.email.setText(food.getEmail());
        holder.name.setText(food.getName());
        holder.password.setText(food.getPassword());
        holder.mobile.setText(food.getMobile());
        holder.address.setText(food.getAddress());
        holder.cpass.setText(food.getCpass());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
