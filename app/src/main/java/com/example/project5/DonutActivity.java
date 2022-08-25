package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

/**
 * This class handles the donut ordering activity.
 * @author Zachary Kowal
 */
public class DonutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] donuts;
    private String[] prices;

    /**
     * Initializes the donut activity.
     * @param savedInstanceState reference to the passed bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        recyclerView = findViewById(R.id.recyclerView);
        donuts = getResources().getStringArray(R.array.donut_offerings);
        prices = getResources().getStringArray(R.array.prices);

        Adapter adapter = new Adapter(this, donuts, prices);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}