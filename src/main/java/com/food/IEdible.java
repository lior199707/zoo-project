package com.food;

/**
 * Interface IEdible, describes objects that can be eaten
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public interface IEdible {

    /**
     * @return the food type of the edible object which activates the method
     */
    public EFoodType getFoodType();
}
