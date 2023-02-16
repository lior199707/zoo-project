package com.diet;

import com.food.EFoodType;
import com.animals.Animal;
import com.food.IEdible;

/**
 * Interface IDiet, describing eating functionality
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public interface IDiet {

    /**
     * @param food the food type of the animal being eaten
     * @return true if this object can eat the food object
     */
    public boolean canEat(EFoodType food);

    /**
     * @param animal  the animal that is eating
     * @param food the food type of the animal that is being eaten
     * @return true if animal can eat food by its food type
     */
    public double eat(Animal animal, IEdible food);
}
