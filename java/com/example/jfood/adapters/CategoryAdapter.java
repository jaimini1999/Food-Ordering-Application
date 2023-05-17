package com.example.jfood.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.R;
import com.example.jfood.models.CategoryModel;
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> ar;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryModel modal1 = ar.get(position);
        holder.id.setText(modal1.getId());
        holder.categoryname.setText(modal1.getCategoryname());
        byte[] foodImage = modal1.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView1.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount()
    {
        return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id,categoryname;
        private ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            categoryname = itemView.findViewById(R.id.categoryname);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }
}
