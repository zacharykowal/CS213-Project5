package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.Toast;
import android.widget.Spinner;

/**
 * Adapter class for working with the Recycler View for donuts.
 * @author Zachary Kowal
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] donuts;
    private String[] prices;
    private Context c;

    /**
     * Constructor to create an adapter object.
     * @param c the context
     * @param donuts list of donuts to be displayed
     * @param prices list of prices for each donut
     */
    public Adapter(Context c, String[] donuts, String[] prices){
        this.c = c;
        this.donuts = donuts;
        this.prices = prices;
    }

    /**
     * Creates the row layout for recycler view.
     * @param parent parent ViewGroup
     * @param viewType type of view
     * @return the item view
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Sets the contents of each recycler view row.
     * @param holder the ViewHolder
     * @param position the index specifying a particular row
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.donutText.setText(donuts[position]);
        holder.priceText.setText(prices[position]);
    }

    /**
     * Getter method to retrieve the amount of items in the recycler view.
     * @return the amount of items
     */
    @Override
    public int getItemCount() {
        return donuts.length;
    }

    /**
     * Inner class to assist with recycler view creation and interaction.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView donutText, priceText;
        Button button;
        Spinner spinner;

        /**
         * Creates a ViewHolder and sets up the recycler view for interactions.
         * @param itemView the view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            donutText = itemView.findViewById(R.id.donutText);
            priceText = itemView.findViewById(R.id.priceText);
            button = itemView.findViewById(R.id.orderButton);
            spinner = itemView.findViewById(R.id.quantitySpinRow);

            String[] quantities = new String[]{"1", "2", "3", "4", "5"};
            ArrayAdapter<String> quantityAdapter = new ArrayAdapter<String>(itemView.getContext(),
                    android.R.layout.simple_spinner_item, quantities);
            quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(quantityAdapter);
            setAddButtonOnClick(itemView);
        }

        /**
         * Method to set up a listener for the add button.
         * @param itemView the view
         */
        private void setAddButtonOnClick(@NonNull View itemView){
            button.setOnClickListener(new View.OnClickListener() {
                /**
                 * Adds the selected donut to the ordering basket.
                 * @param view the add button that was clicked
                 */
                @Override
                public void onClick(View view) {

                    String[] split = (donutText.getText().toString()).split(" ");
                    String donutType = split[split.length - 1];
                    String donutFlavor = "";
                    for(int i = 0; i < split.length - 1; i++){
                        if(i == split.length - 2){
                            donutFlavor = donutFlavor + split[i];
                            break;
                        }
                        donutFlavor = donutFlavor + split[i] + " ";
                    }

                    Donut d = new Donut(donutType, donutFlavor,
                            Integer.parseInt(spinner.getSelectedItem().toString()));
                    BasketActivity.basketOrder.add(d);
                    Toast.makeText(itemView.getContext(), "Donut added to order.",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
