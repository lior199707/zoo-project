package com.diet;

import com.animals.Animal;
import com.food.EFoodType;
import com.food.IEdible;

/**
 * class Omnivore, handles animal that eat both meat and vegetables
 * @see com.diet.IDiet
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Omnivore implements IDiet {
    /**
     * delegator ,handles meat eating processes
     */
    private final IDiet carnivore;
    /**
     * delegator ,handles vegetables eating processes
     */
    private final IDiet herbivore;

    /**
     * Omnivore constructor
     */
    public Omnivore(){
        this.carnivore = new Carnivore();
        this.herbivore = new Herbivore();
    }

    /**
     * check if this carnivore object is the same reference as o
     * @param o the second object
     * @return true if the same reference, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Omnivore omnivore = (Omnivore) o;

        return carnivore.equals(omnivore.carnivore) || herbivore.equals(omnivore.herbivore);
    }

    /**
     * toString method of Herbivore.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Omnivore]";
    }

    /**
     * if the animal can eat the food, and the food and the animal aren't pointing to the same animal
     * if the food is meat type uses Carnivore eat, else if the food is vegetable type uses herbivore eat
     * returns the weight the eating animal has gained in the process of eating, else returns 0
     * @param animal the animal that eats
     * @param food the animal that is being eaten
     * @return double the weight the animal gained in the process of eating.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        EFoodType foodType = food.getFoodType();
        if (canEat(foodType) && !(animal == food)) {
            if (foodType == EFoodType.MEAT) {
                return carnivore.eat(animal, food);
            } else {
                return herbivore.eat(animal, food);
            }
        }
        return 0;
    }

    /**
     * checks if this object can eat the food
     * @param food reference to EFoodType
     * @return true if the food is vegetable or meat, false otherwise (if the food is NOTFOOD type)
     * @see com.food.EFoodType
     */
    @Override
    public boolean canEat(EFoodType food) {
        return food == EFoodType.MEAT || food == EFoodType.VEGETABLE;
    }

}