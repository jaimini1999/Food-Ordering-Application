package com.example.jfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jfood.adapters.OrderAdapter;
import com.example.jfood.adapters.OrderAdapterG;
import com.example.jfood.models.OrderModel;

import java.util.ArrayList;

public class ManageOrder extends AppCompatActivity {

    EditText edt16;
    Button search6;
    private ArrayList<OrderModel> ar;
    private DBHelper db;
    private OrderAdapterG oa;
    private GridView grid;
    DBHelper DB;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_gridview);

        edt16 = findViewById(R.id.edt16);
        search6 = findViewById(R.id.search6);
        grid = findViewById(R.id.grid);

        ar = new ArrayList<>();
        db = new DBHelper(ManageOrder.this);

        ar = db.readOrder();

        oa = new OrderAdapterG(ManageOrder.this, R.layout.order_data_show, ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(oa);

        search6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar = new ArrayList<>();
                db = new DBHelper(ManageOrder.this);
                String s = edt16.getText().toString();
                ar = db.searchOrder(s);

                oa = new OrderAdapterG(ManageOrder.this, R.layout.order_data_show, ar);
                grid = findViewById(R.id.grid);

                grid.setAdapter(oa);

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageOrder.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update

                            db = new DBHelper(ManageOrder.this);

                            Cursor c = db.dataReadOrder();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ManageOrder.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.dataReadOrder();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();

            }
        });
    }

    private void showDialogUpdate(ManageOrder manageOrder, Integer orderid) {

        Session session=new Session(ManageOrder.this);
        session.setid(""+orderid);

        Toast.makeText(getApplicationContext(), "update" + orderid, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), EditOrder.class);
        startActivity(intent);
    }

    private void showDialogDelete(Integer orderid) {

        db = new DBHelper(ManageOrder.this);
        db.deleteOrderData(orderid);
        Toast.makeText(getApplicationContext(), "delete" + orderid, Toast.LENGTH_SHORT).show();

        updateFoodList();
    }

    private void updateFoodList() {
        // get all data from sqlite

        ar = new ArrayList<>();
        db = new DBHelper(ManageOrder.this);

        ar = db.readOrder();

        oa = new OrderAdapterG(ManageOrder.this, R.layout.order_data_show, ar);
        grid = findViewById(R.id.grid);

        grid.setAdapter(oa);
    }
}
