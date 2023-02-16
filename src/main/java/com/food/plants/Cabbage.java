package com.food.plants;

/**
 * Cabbage class, a Plant descendant.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Cabbage extends Plant {
    /**
     * Singleton Cabbage instance.
     */
    private static Cabbage instance = null;
    /**
     * Cabbage constructor.
     */
    private Cabbage() { }

    /**
     * getInstance
     * @return Cabbage object if there is no cabbage instance.
     */
    public static Cabbage getInstance() {
        if (instance == null)
            instance = new Cabbage();
        return instance;
    }

    /**
     * override abstract class Food animalShortPathName().
     * @return String representation of the short path name for loading Cabbage image.
     */
    @Override
    public String foodShortPathName() {
        return "Cabbage";
    }

}
