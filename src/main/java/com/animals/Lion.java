package com.animals;

import com.diet.Carnivore;
import com.food.EFoodType;
import com.food.IEdible;
import com.mobility.Point;

import java.util.Random;

/**
 * Lion class representing the Lion animal. It can roar!
 * @see com.animals.AnimalRoar
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Lion extends AnimalRoar {
     /**
     * constant double value of the default weight of a Lion.
     */
    private static final double DEFAULT_WEIGHT = 408.2;
    /**
     * constant Point coordinates of the default starting location of a Lion.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(20, 0);
    /**
     * constant integer value of the default scar count of a Lion.
     */
    private static final int DEFAULT_SCAR_COUNT = 0;
    /**
     * constant double value of the coefficient used for weight calculation.
     */
    private static final double SIZE_COEFFICIENT = 0.8;
    /**
     * constant int value of the default size of a Lion.
     */
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * integer value of the scar count of a Lion.
     */
    private int scarCount;

    /**
     * Lion constructor.
     * setting default location, default scar count and diet!
     * passing name, location, size, horizontal speed, vertical speed and color to super.
     * @see com.animals.AnimalRoar
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */

    public Lion(String name, int size, int horSpeed, int verSpeed, String col) {
        super(name, DEFAULT_STARTING_LOCATION, size, horSpeed, verSpeed, col);
        setWeight(getSize() * 0.8);
        setDiet(new Carnivore());
        scarCount = DEFAULT_SCAR_COUNT;
        loadImages(animalShortPathName());
    }

    /**
     * Lion constructor.
     * setting default size.
     * passing name, horizontal speed and vertical speed to main constructor.
     * @param name String value of the animal's name, should contain only letters.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     */
    public Lion(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * Lion copy constructor
     * @param other Lion object to copy.
     */
    public Lion(Lion other){
        super(other);
        this.scarCount = other.scarCount;
    }

    /**
     * class Lion static method.
     * @return Point object of the default starting location of a Lion.
     */
    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    /**
     * scar count getter.
     * @return integer value of the lion's scar count.
     */
    public int getScarCount() {
        return scarCount;
    }

    /**
     * class Lion static method
     * @return constant integer value of the coefficient used for weight calculation.
     */
    public static int getDefaultScarCount() {
        return DEFAULT_SCAR_COUNT;
    }

    /**
     * class Lion static method
     * @return constant double value of the coefficient used for weight calculation.
     */
    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }

    /**
     * roar implementation of lion.
     */
    @Override
    public void roar() {
        System.out.println(getName() + " Roars, then stretches and shakes its mane");
    }

    /**
     * equals method of Lion object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Lion lion = (Lion) o;

        return getScarCount() == lion.getScarCount();
    }

    /**
     * toString of lion.
     * @return String representation of Lion.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Animal clone() {
        return new Lion(this);
    }

    @Override
    public String animalShortPathName() {
        return "lio";
    }

    /**
     * if eat of Animal was successful, randomly increase the scar count.
     * @see com.animals.Animal eat() method.
     * @param food an edible type of animal to eat.
     * @return boolean value if the lion ate or not.
     */
    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = super.eat(food);
        if (isSuccess){
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                this.scarCount++;
            }
        }
        return isSuccess;
    }

    /**
     * food type getter.
     * @return EFoodType.NOTFOOD
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.NOTFOOD;
    }

    /**
     * override interface IAnimalBehavior getAnimalName().
     * @return String representation of the animal natural name.
     */
    @Override
    public String getAnimalName() {
        return "Lion";
    }

}