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
import com.example.jfood.models.MainModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<MainModel> ar;
    private Context context;

    public MainAdapter(ArrayList<MainModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_data_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MainModel modal = ar.get(position);
        holder.userid.setText(modal.getUserid());
        holder.email.setText(modal.getEmail());
        holder.name.setText(modal.getName());
        holder.password.setText(modal.getPassword());
        holder.mobile.setText(modal.getMobile());
        holder.address.setText(modal.getAddress());
        holder.cpass.setText(modal.getCpass());
        byte[] foodImage = modal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount()
    {

        return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userid,email, name, password, mobile, address, cpass;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userid = itemView.findViewById(R.id.userid);
            email = itemView.findViewById(R.id.email);
            name = itemView.findViewById(R.id.name);
            password = itemView.findViewById(R.id.password);
            mobile = itemView.findViewById(R.id.mobile);
            address = itemView.findViewById(R.id.address);
            cpass = itemView.findViewById(R.id.cpass);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
