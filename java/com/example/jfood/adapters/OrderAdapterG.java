package com.example.jfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jfood.R;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapterG extends BaseAdapter {

    private ArrayList<OrderModel> ar;
    private Context context;
    private  int layout;
    private ArrayList<OrderModel> foodsList;

    public OrderAdapterG(Context context, int layout, ArrayList<OrderModel> foodsList) {
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
        private TextView orderid,itemid,email,price,quantity,deliveryaddress,total;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.orderid = (TextView) row.findViewById(R.id.orderid);
            holder.itemid = (TextView) row.findViewById(R.id.oitemid);
            holder.email = (TextView) row.findViewById(R.id.oemail);
            holder.price =  (TextView) row.findViewById(R.id.oprice);
            holder.quantity =  (TextView) row.findViewById(R.id.oquantity);
            holder.deliveryaddress =  (TextView) row.findViewById(R.id.odelivery);
            holder.total = (TextView) row.findViewById(R.id.ototal);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        OrderModel food = foodsList.get(position);

        holder.orderid.setText(food.getOrderid());
        holder.itemid.setText(food.getOitemid());
        holder.email.setText(food.getOemail());
        holder.price.setText(food.getOprice());
        holder.quantity.setText(food.getOquantity());
        holder.deliveryaddress.setText(food.getOdelivery());
        holder.total.setText(food.getOtotal());

        return row;
    }
}
