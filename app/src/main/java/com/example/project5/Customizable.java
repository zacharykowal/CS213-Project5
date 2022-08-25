package com.example.project5;

/**
 * Interface which enforces the classes that implement it to provide addition and removal functionality.
 * (Must be able to add/remove add-ins for coffee, menu items for orders, and orders for store orders.)
 * @author Zachary Kowal
 */
public interface Customizable {

    /**
     * Provides the add functionality described above.
     * @param obj the object being added
     * @return true if successful, false otherwise
     */
    boolean add(Object obj);

    /**
     * Provides the removal functionality as described above.
     * @param obj the object being removed
     * @return true if successful, false otherwise
     */
    boolean remove(Object obj);

}
