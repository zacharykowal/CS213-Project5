package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * This class handles the main menu activity.
 * @author Zachary Kowal
 */
public class MainActivity extends AppCompatActivity {

    private Button coffee;
    private Button donuts;
    private Button basket;
    private Button storeOrders;

    /**
     * Initializes the main menu activity and sets up the necessary listeners.
     * @param savedInstanceState the reference to the passed bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coffee = findViewById(R.id.coffeeButton);
        coffee.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking the order coffee button
             * @param view the clicked button
             */
            @Override
            public void onClick(View view) {
                showCoffee();
            }
        });

        donuts = findViewById(R.id.donutButton);
        donuts.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking the order donuts button
             * @param view the clicked button
             */
            @Override
            public void onClick(View view) {
                showDonuts();
            }
        });

        basket = findViewById(R.id.basketButton);
        basket.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking the ordering basket button
             * @param view the clicked button
             */
            @Override
            public void onClick(View view) {
                showBasket();
            }
        });

        storeOrders = findViewById(R.id.storeButton);
        storeOrders.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking the store orders button
             * @param view the clicked button
             */
            @Override
            public void onClick(View view) {
                showStore();
            }
        });
    }

    /**
     * Opens and shows the coffee ordering activity
     */
    private void showCoffee(){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Opens and shows the donut ordering activity
     */
    private void showDonuts(){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Opens and shows the ordering basket activity
     */
    private void showBasket(){
        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }

    /**
     * Opens and shows the store orders activity
     */
    private void showStore(){
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }
}