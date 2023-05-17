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

import com.example.jfood.R;
import com.example.jfood.models.FavoriteModel;
import com.example.jfood.models.FoodItemModel;

import java.util.ArrayList;

public class FavoriteAdapter  extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private ArrayList<FavoriteModel> ar;
    private Context context;

    public FavoriteAdapter(ArrayList<FavoriteModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_data_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {

        FavoriteModel modal5 = ar.get(position);
        holder.itemid.setText(modal5.getItemid());
        holder.email.setText(modal5.getEmail());
        holder.fname.setText(modal5.getFname());
        holder.fdescription.setText(modal5.getFdescription());
        holder.fprice.setText(modal5.getFprice());
        holder.fcname.setText(modal5.getFcname());
        byte[] foodImage = modal5.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView2.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount()
    {

            return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemid,email,fname,fdescription,fprice,fcname;
        private ImageView imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemid = itemView.findViewById(R.id.itemid);
            email = itemView.findViewById(R.id.email);
            fname = itemView.findViewById(R.id.fname);
            fdescription = itemView.findViewById(R.id.fdescription);
            fprice = itemView.findViewById(R.id.fprice);
            fcname = itemView.findViewById(R.id.fcname);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }
}
