package com.example.jfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.R;
import com.example.jfood.models.FoodItemModel;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private ArrayList<OrderModel> ar;
    private Context context;

    public OrderAdapter(ArrayList<OrderModel> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_data_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderModel modal3 = ar.get(position);
        holder.orderid.setText(modal3.getOrderid());
        holder.oitemid.setText(modal3.getOitemid());
        holder.oemail.setText(modal3.getOemail());
        holder.oprice.setText(modal3.getOprice());
        holder.oquantity.setText(modal3.getOquantity());
        holder.odelivery.setText(modal3.getOdelivery());
        holder.ototal.setText(modal3.getOtotal());
    }
    @Override
    public int getItemCount() {
        return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView orderid,oitemid,oemail,oprice,oquantity,odelivery,ototal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderid = itemView.findViewById(R.id.orderid);
            oitemid = itemView.findViewById(R.id.oitemid);
            oemail = itemView.findViewById(R.id.oemail);
            oprice = itemView.findViewById(R.id.oprice);
            oquantity = itemView.findViewById(R.id.oquantity);
            odelivery = itemView.findViewById(R.id.odelivery);
            ototal = itemView.findViewById(R.id.ototal);
        }
    }
}
