package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.text.NumberFormat;

/**
 * This class handles the coffee ordering activity.
 * @author Zachary Kowal
 */
public class CoffeeActivity extends AppCompatActivity {

    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double ADD_IN_PRICE = 0.30;

    private CheckBox caramelCheck;
    private CheckBox creamCheck;
    private CheckBox syrupCheck;
    private CheckBox milkCheck;
    private CheckBox whippedCheck;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;
    private TextView subtotal;

    /**
     * Initializes the coffee activity and sets up the necessary listeners.
     * @param savedInstanceState reference to the passed bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        caramelCheck = findViewById(R.id.caramelCheck);
        creamCheck = findViewById(R.id.creamCheck);
        syrupCheck = findViewById(R.id.syrupCheck);
        milkCheck = findViewById(R.id.milkCheck);
        whippedCheck = findViewById(R.id.whippedCheck);

        subtotal = findViewById(R.id.subtotal);

        String[] sizes = new String[]{"Short", "Tall", "Grande", "Venti"};
        sizeSpinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        String[] quantities = new String[]{"1", "2", "3", "4", "5"};
        quantitySpinner = findViewById(R.id.quantitySpinner);
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, quantities);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);

        Button add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles clicking the add to order button.
             * @param view the button clicked
             */
            @Override
            public void onClick(View view) {
                addToOrder();
            }
        });

        caramelCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Handles changing a checkbox
             * @param compoundButton the checkbox
             * @param b status of checkbox
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTotal();
            }
        });

        creamCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Handles changing a checkbox
             * @param compoundButton the checkbox
             * @param b status of checkbox
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTotal();
            }
        });

        syrupCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Handles changing a checkbox
             * @param compoundButton the checkbox
             * @param b status of checkbox
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTotal();
            }
        });

        milkCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Handles changing a checkbox
             * @param compoundButton the checkbox
             * @param b status of checkbox
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTotal();
            }
        });

        whippedCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Handles changing a checkbox
             * @param compoundButton the checkbox
             * @param b status of checkbox
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateTotal();
            }
        });

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles when a new coffee size is selected from the spinner
             * @param adapterView the AdapterView
             * @param view the spinner
             * @param i the position of the view in the adapter
             * @param l the id of the item clicked
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateTotal();
            }

            /**
             * Handles when nothing is selected from the spinner
             * @param adapterView the AdapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateTotal();
            }
        });

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles when a new quantity is selected from the spinner
             * @param adapterView the AdapterView
             * @param view the spinner
             * @param i the position of the view in the adapter
             * @param l the id of the item clicked
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateTotal();
            }

            /**
             * Handles when nothing is selected from the spinner
             * @param adapterView the AdapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateTotal();
            }
        });
    }

    /**
     * Adds the specified coffee to the ordering basket.
     */
    private void addToOrder(){
        Coffee c = new Coffee(sizeSpinner.getSelectedItem().toString(),
                Integer.parseInt(quantitySpinner.getSelectedItem().toString()));

        if(caramelCheck.isChecked()){
            c.add("Caramel");
        }
        if(creamCheck.isChecked()){
            c.add("Cream");
        }
        if(syrupCheck.isChecked()){
            c.add("Syrup");
        }
        if(milkCheck.isChecked()){
            c.add("Milk");
        }
        if(whippedCheck.isChecked()){
            c.add("Whipped Cream");
        }

        BasketActivity.basketOrder.add(c);
        Toast.makeText(CoffeeActivity.this, "Coffee added to order.",
                Toast.LENGTH_LONG).show();
    }

    /**
     * Updates the running total of the coffee upon any changes to the coffee options.
     */
    private void updateTotal(){
        int addCount = 0;
        double price;

        if(caramelCheck.isChecked()){
            addCount++;
        }
        if(creamCheck.isChecked()){
            addCount++;
        }
        if(syrupCheck.isChecked()){
            addCount++;
        }
        if(milkCheck.isChecked()){
            addCount++;
        }
        if(whippedCheck.isChecked()){
            addCount++;
        }

        if(sizeSpinner.getSelectedItem().toString().equals("Short")){
            price = SHORT_PRICE;
        }else if(sizeSpinner.getSelectedItem().toString().equals("Tall")){
            price = TALL_PRICE;
        }else if(sizeSpinner.getSelectedItem().toString().equals("Grande")){
            price = GRANDE_PRICE;
        }else{
            price = VENTI_PRICE;
        }

        double total = (Integer.parseInt(quantitySpinner.getSelectedItem().toString())) *
                (price + (addCount * ADD_IN_PRICE));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(total));
    }
}