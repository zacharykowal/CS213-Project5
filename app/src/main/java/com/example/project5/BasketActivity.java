package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

/**
 * This class handles the ordering basket activity.
 * @author Zachary Kowal
 */
public class BasketActivity extends AppCompatActivity {

    private static final double NJ_SALES_TAX = 0.06625;

    public static Order basketOrder = new Order(0);
    private Order orderCopy;
    private ListView itemList;
    private TextView subtotal;
    private TextView salesTax;
    private TextView total;
    private Button add;

    /**
     * Initializes the ordering basket activity and sets up the necessary listeners.
     * @param savedInstanceState reference to the passed bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        orderCopy = new Order(basketOrder);

        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, basketOrder.getMenuItems());

        subtotal = findViewById(R.id.subtotalBasket);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);

        updateTotals();

        itemList = findViewById(R.id.itemList);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Handles the action of clicking an item in the item list.
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

        add = findViewById(R.id.placeOrder);
        add.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the action of clicking the add to order button.
             * @param view the button clicked
             */
            @Override
            public void onClick(View view) {
                if(basketOrder.getMenuItems().size() == 0){
                    AlertDialog ad = new AlertDialog.Builder(BasketActivity.this)
                            .setTitle("Error").setMessage("Cannot place an empty order.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                /**
                                 * Handles clicking the "Ok" button in the alert
                                 * @param dialogInterface the DialogInterface object
                                 * @param i the button that was clicked
                                 */
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    return;
                                }
                            }).create();
                    ad.show();
                    return;
                }
                StoreActivity.so.add(orderCopy);
                basketOrder.getMenuItems().clear();
                itemList.invalidateViews();
                updateTotals();
                Toast.makeText(BasketActivity.this, "Order placed.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Removes a specified item from the item list if the confirm button is clicked.
     * @param position the position of the item to be removed
     */
    private void confirmRemove(int position){
        AlertDialog ad = new AlertDialog.Builder(BasketActivity.this).setTitle("Confirm")
                .setMessage("Remove item from the ordering basket?").setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            /**
                             * Removes the item if the confirm button is clicked on the alert.
                             * @param dialogInterface the DialogInterface object
                             * @param i the button that was clicked
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                basketOrder.remove(itemList.getItemAtPosition(position));
                                orderCopy = new Order(basketOrder);
                                itemList.invalidateViews();
                                updateTotals();
                                Toast.makeText(BasketActivity.this, "Item removed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /**
                     * Does not remove the item if the "No" button is clicked.
                     * @param dialogInterface the DialogInterface
                     * @param i the button clicked
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(BasketActivity.this, "Item not removed.",
                                Toast.LENGTH_LONG).show();
                    }
                }).create();
        ad.show();
    }

    /**
     * Updates the TextViews upon any change in the item list.
     */
    private void updateTotals(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(basketOrder.getOrderTotal()));
        double tax = basketOrder.getOrderTotal() * NJ_SALES_TAX;
        salesTax.setText(formatter.format(tax));
        total.setText(formatter.format(tax + basketOrder.getOrderTotal()));
    }
}