package com.example.project5;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This class represents an order which can be created and edited by the user.
 * @author Zachary Kowal
 */
public class Order implements Customizable{

    private static final double NJ_SALES_TAX = 0.06625;
    private int orderNumber;
    private ArrayList<MenuItem> items;

    /**
     * Constructor to create an order from an order number.
     * @param orderNumber the order number
     */
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
        this.items = new ArrayList<>();
    }

    /**
     * Copy constructor for an order object
     * @param o the order to be copied from
     */
    public Order(Order o){
        this.orderNumber = o.orderNumber;
        ArrayList<MenuItem> copy = new ArrayList<MenuItem>(o.getMenuItems().size());
        for(MenuItem item : o.getMenuItems()){
            if(item instanceof Donut){
                copy.add(new Donut((Donut) item));
            }else{
                copy.add(new Coffee((Coffee) item));
            }
        }
        this.items = copy;
    }

    /**
     * Adds a menu item to the order.
     * @param obj the menu item being added
     * @return true if the item was successfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        return items.add((MenuItem) obj);
    }

    /**
     * Removes a menu item from the order
     * @param obj the item being removed
     * @return true if successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        return items.remove((MenuItem) obj);
    }

    /**
     * Sets the order's number after creation
     * @param i the new order number
     */
    public void setOrderNo(int i){
        this.orderNumber = i;
    }

    /**
     * Retrieves the order's menu items
     * @return the ArrayList containing the menu items
     */
    public ArrayList<MenuItem> getMenuItems(){
        return items;
    }

    /**
     * Calculates the total price of the order
     * @return the calculated price
     */
    public double getOrderTotal(){
        double total = 0;
        for(int i = 0; i < this.items.size(); i++){
            total = total + this.items.get(i).itemPrice();
        }
        return total;
    }

    /**
     * Converts an order to a string
     * @return the converted string
     */
    @Override
    public String toString(){
        String str = "ORDER #" + this.orderNumber + ":\n";
        for(int i = 0; i < this.items.size(); i++){
            str = str + this.items.get(i) + "\n";
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double tax = this.getOrderTotal() * NJ_SALES_TAX;
        str = str + "ORDER TOTAL: " + formatter.format(this.getOrderTotal() + tax);
        return str;
    }
}
