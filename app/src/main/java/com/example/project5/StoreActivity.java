package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * This class handles the store orders activity.
 * @author Zachary Kowal
 */
public class StoreActivity extends AppCompatActivity {

    public static StoreOrders so = new StoreOrders();
    private ListView orderList;

    /**
     * Initializes the store orders activity and sets up the necessary listeners.
     * @param savedInstanceState the reference to the passed bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        if(so.getOrders().size() == 0){
            return;
        }

        for(int i = 0; i < so.getOrders().size(); i++){
            so.getOrders().get(i).setOrderNo(i + 1);
        }

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < so.getOrders().size(); i++){
            list.add(so.getOrders().get(i).toString());
        }

        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this,
                android.R.layout.simple_list_item_1, so.getOrders());
        orderList = findViewById(R.id.orderList);
        orderList.setAdapter(adapter);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Handles clicking an order from the order list
             * @param adapterView the AdapterView object
             * @param view the list clicked
             * @param i the position of the view in the adapter
             * @param l the row id of the item clicked
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                confirmRemove(i);
            }
        });
    }

    /**
     * Handles the removal of an order from the store orders list.
     * @param position the position of the order being removed
     */
    private void confirmRemove(int position){
        AlertDialog ad = new AlertDialog.Builder(StoreActivity.this).setTitle("Confirm")
                .setMessage("Remove order?").setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            /**
                             * Handles clicking the "Yes" button on the alert
                             * @param dialogInterface the DialogInterface object
                             * @param i the button that was clicked
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                so.remove(orderList.getItemAtPosition(position));
                                orderList.invalidateViews();
                                Toast.makeText(StoreActivity.this, "Order removed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /**
                     * Handles clicking the "No" button on the alert
                     * @param dialogInterface the DialogInterface object
                     * @param i the button that was clicked
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(StoreActivity.this, "Order not removed.",
                                Toast.LENGTH_LONG).show();
                    }
                }).create();
        ad.show();
    }
}