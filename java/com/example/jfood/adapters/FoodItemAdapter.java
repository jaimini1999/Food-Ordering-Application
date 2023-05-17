package com.example.jfood.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.AddToCart;
import com.example.jfood.R;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder>{

    private ArrayList<FoodItemModel> ar;
    private Context context;

    public FoodItemAdapter(ArrayList<FoodItemModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_data_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.ViewHolder holder, int position) {
        FoodItemModel modal2 = ar.get(position);
        holder.itemid.setText(modal2.getItemid());
        holder.fname.setText(modal2.getFname());
        holder.fdescription.setText(modal2.getFdescription());
        holder.fprice.setText(modal2.getFprice());
        holder.fcname.setText(modal2.getFcname());
        byte[] foodImage = modal2.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView2.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount()
    {
        return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemid,fname,fdescription,fprice,fcname;
        private ImageView imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemid = itemView.findViewById(R.id.itemid);
            fname = itemView.findViewById(R.id.fname);
            fdescription = itemView.findViewById(R.id.fdescription);
            fprice = itemView.findViewById(R.id.fprice);
            fcname = itemView.findViewById(R.id.fcname);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }
}
