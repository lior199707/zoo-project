package com.diet;

import com.animals.Animal;
import com.food.EFoodType;
import com.food.IEdible;

/**
 * class Carnivore, handles meat eating animals
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Carnivore implements IDiet {

    /**
     * Carnivore constructor
     */
    public Carnivore(){}

    /**
     * check if this carnivore object is the same reference as obj
     * @param obj the second object
     * @return true if the same reference, false otherwise
     */
    @Override
    public boolean equals(Object obj) {return super.equals(obj);
    }

    /**
     * toString method of Carnivore.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Carnivore]";
    }

    /**
     * if the animal can eat the food, and the food and the animal aren't pointing to the same animal
     * returns the weight the eating animal has gained in the process of eating, else returns 0
     * @param animal  the animal that eats
     * @param food the animal that is being eaten
     * @return double the weight the animal gained in the process of eating.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodType()) && !(animal == food)) {
            return (animal.getWeight() * 0.1);
        }
        return 0;
    }

    /**
     * checks if this object can eat the food
     * @param food , reference to EFoodType
     * @return true if the food is meat, false otherwise
     */
    @Override
    public boolean canEat(EFoodType food) {
        return food == EFoodType.MEAT;
    }

}
