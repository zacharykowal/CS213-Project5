package com.example.project5;

/**
 * This class represents a donut that the user can order.
 * @author Zachary Kowal
 */
public class Donut extends MenuItem {

    private String type;
    private String flavor;
    private double price;
    private int quantity;

    private static final double YEAST_PRICE = 1.59;
    private static final double CAKE_PRICE = 1.79;
    private static final double HOLE_PRICE = 0.39;

    /**
     * Constructor to create a donut object from a type, flavor, and quantity.
     * @param type the type of donut
     * @param flavor the flavor of the donut
     * @param quantity the quantity of the donut
     */
    public Donut(String type, String flavor, int quantity){
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
        this.price = this.itemPrice();
    }

    /**
     * Copy constructor for a donut object
     * @param d the donut to be copied from
     */
    public Donut(Donut d){
        this.type = d.type;
        this.flavor = d.flavor;
        this.price = d.price;
        this.quantity = d.quantity;
    }

    /**
     * Calculate the price of a donut.
     * @return the calculated price
     */
    @Override
    public double itemPrice() {
        if(this.type.equals("Yeast")){
            return YEAST_PRICE * this.quantity;
        }else if(this.type.equals("Cake")) {
            return CAKE_PRICE * this.quantity;
        }
        return HOLE_PRICE * this.quantity;
    }

    /**
     * Converts a donut object into a String with the relevant donut information
     * @return the converted String
     */
    @Override
    public String toString() {
        return this.flavor + " " + this.type + " (" + this.quantity + ")";
    }
}
