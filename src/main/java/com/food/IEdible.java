package com.food;

/**
 * Interface IEdible, describes objects that can be eaten
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IEdible {

    /**
     * @return the food type of the edible object which activates the method
     */
    public EFoodType getFoodType();
}
