package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;

/**
 * Elephant class representing the elephant animal. It can chew!
 * @see com.animals.AnimalChew
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Elephant extends AnimalChew {
    /**
     * constant double value of the default weight of an elephant.
     */
    private static final double DEFAULT_WEIGHT = 500.00;
    /**
     * constant double value of the coefficient used for weight calculation.
     */
    private static final double SIZE_COEFFICIENT = 10;
    /**
     * constant int value of the default size of an Elephant.
     */
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * constant Point coordinates of the default starting location of an Elephant.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 90);
    /**
     * constant double value of the default trunk length of an Elephant.
     */
    private static final double DEFAULT_TRUNK_LENGTH = 1.0;
    /**
     * double value of the trunk length of an Elephant.
     */
    private double trunkLength;

    /**
     * Elephant constructor.
     * setting default location, default trunk length and diet!
     * passing name, location, size, horizontal speed, vertical speed and color to super.
     *
     * @see com.animals.AnimalChew
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */

    public Elephant(String name, int size, int horSpeed, int verSpeed, String col) {
        super(name, DEFAULT_STARTING_LOCATION, size, horSpeed, verSpeed, col);
        setWeight(getSize() * 10);
        setTrunkLength(DEFAULT_TRUNK_LENGTH);
        setDiet(new Herbivore());
        loadImages(animalShortPathName());
    }

    /**
     * Elephant constructor.
     * setting default size.
     * passing name, horizontal speed and vertical speed to main constructor.
     * @param name String value of the animal's name, should contain only letters.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     */
    public Elephant(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * Elephant copy constructor
     * @param other Elephant object to copy.
     */
    public Elephant(Elephant other){
        super(other);
        this.trunkLength = other.getTrunkLength();
    }

    /**
     * class Elephant static method.
     * @return Point object of the default starting location of an Elephant.
     */
    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    /**
     * trunk length getter.
     * @return double value of the trunk length.
     */
    public double getTrunkLength() {
        return trunkLength;
    }

    /**
     * class Elephant static method.
     * @return double value of the default trunk length for an elephant.
     */
    public static double getDefaultTrunkLength() {
        return DEFAULT_TRUNK_LENGTH;
    }

    /**
     * class Elephant static method
     * @return constant double value of the coefficient used for weight calculation.
     */
    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }

    /**
     * trunk length setter.
     * checks if the trunk length is valid (between min/max values).
     * if so, assigns the parameter value to the trunkLength field.
     * otherwise, it will not update it.
     *
     * @param trunkLength double value to set.
     * @return boolean value if set was successful or not.
     */
    public boolean setTrunkLength(double trunkLength) {
        boolean isSuccess = false;
        double MIN_LENGTH = 0.5, MAX_LENGTH = 3.0;
        if (trunkLength >= MIN_LENGTH && trunkLength <= MAX_LENGTH) {
            this.trunkLength = trunkLength;
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * chew implementation of an elephant.
     */
    @Override
    public void chew() {
        System.out.println(getName() + " Trumpets with joy while flapping its ears, then chews");
    }

    /**
     * equals method of Elephant object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Elephant elephant = (Elephant) o;

        return Double.compare(elephant.getTrunkLength(), getTrunkLength()) == 0;
    }

    /**
     * toString of elephant.
     * @return String representation of Elephant.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Animal clone() {
        return new Elephant(this);
    }

    /**
     * override abstract class Animal animalShortPathName().
     * @return String representation of the short path name for loading Elephant images.
     */
    @Override
    public String animalShortPathName() {
        return "elf";
    }

    /**
     * override interface IAnimalBehavior getAnimalName().
     * @return String representation of the animal natural name.
     */
    @Override
    public String getAnimalName() {
        return "Elephant";
    }

}