package com.example.project5;

import java.util.ArrayList;

/**
 * This class represents a coffee in which the user can order.
 * @author Zachary Kowal
 */
public class Coffee extends MenuItem implements Customizable{

    private String size;
    private ArrayList<String> addIns;
    private double price;
    private int quantity;

    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double ADD_IN_PRICE = 0.30;

    /**
     * Creates a coffee object from a size and quantity.
     * @param size the size of the coffee
     * @param quantity the quantity of coffee
     */
    public Coffee(String size, int quantity){
        this.size = size;
        this.quantity = quantity;
        this.addIns = new ArrayList<>();
        this.price = this.itemPrice();
    }

    /**
     * Copy constructor for a coffee object
     * @param c the coffee to be copied from
     */
    public Coffee(Coffee c){
        this.size = c.size;
        this.price = c.price;
        this.quantity = c.quantity;
        ArrayList<String> copy = new ArrayList<String>(c.addIns.size());
        copy.addAll(c.addIns);
        this.addIns = copy;
    }

    /**
     * Adds an add-in to the coffee.
     * @param obj the add-in being added
     * @return true if successful add, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(!addIns.contains((String) obj)){
            addIns.add((String) obj);
            this.price = this.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Removes an add-in from the coffee
     * @param obj the add-in being removed
     * @return true if successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(addIns.remove((String) obj)){
            this.price = this.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Calculates the price of the coffee
     * @return the price
     */
    @Override
    public double itemPrice() {
        if(this.size.equals("Short")){
            return (this.quantity * (SHORT_PRICE + (this.addIns.size() * ADD_IN_PRICE)));
        }else if(this.size.equals("Tall")){
            return (this.quantity * (TALL_PRICE + (this.addIns.size() * ADD_IN_PRICE)));
        }else if(this.size.equals("Grande")){
            return (this.quantity * (GRANDE_PRICE + (this.addIns.size() * ADD_IN_PRICE)));
        }
        return (this.quantity * (VENTI_PRICE + (this.addIns.size() * ADD_IN_PRICE)));
    }

    /**
     * Converts the coffee object into a String containing the relevant information.
     * @return the converted String
     */
    @Override
    public String toString() {
        return "Coffee " + "(" + this.quantity + ") " + this.size + " " + this.addIns;
    }
}
