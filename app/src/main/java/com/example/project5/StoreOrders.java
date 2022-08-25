package com.example.project5;

import java.util.ArrayList;

/**
 * This class represents the orders that have been confirmed by the store.
 * @author Zachary Kowal
 */
public class StoreOrders implements Customizable{

    private ArrayList<Order> orders;

    /**
     * Constructor to create the StoreOrders object and initialize the orders ArrayList.
     */
    public StoreOrders(){
        this.orders = new ArrayList<>();
    }

    /**
     * Retrieves the orders currently confirmed by the store.
     * @return the orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order to the ArrayList of orders
     * @param obj the order being added
     * @return true if successful, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        return orders.add((Order) obj);
    }

    /**
     * Removes an order from the ArrayList of orders
     * @param obj the order being removed
     * @return true if successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        return orders.remove((Order) obj);
    }
}
