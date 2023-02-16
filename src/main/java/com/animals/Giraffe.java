package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;

/**
 * Giraffe class representing the Giraffe animal. It can chew!
 * @see com.animals.AnimalChew
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Giraffe extends AnimalChew {
    /**
     * constant double value of the default weight of a Giraffe.
     */
    private static final double DEFAULT_WEIGHT = 450.00;
    /**
     * constant double value of the default neck length of a Giraffe.
     */
    private static final double DEFAULT_NECK_LENGTH = 1.5;
    /**
     * constant double value of the coefficient used for weight calculation.
     */
    private static final double SIZE_COEFFICIENT = 2.2;
    /**
     * constant Point coordinates of the default starting location of a Giraffe.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 0);
    /**
     * constant int value of the default size of a Giraffe.
     */
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * double value of the neck length of a Giraffe.
     */
    private double neckLength;

    /**
     * Giraffe constructor.
     * setting default location, default neck length and diet!
     * passing name, location, size, horizontal speed, vertical speed and color to super.
     *
     * @see com.animals.AnimalChew
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public Giraffe(String name, int size, int horSpeed, int verSpeed, String col){
        super(name, DEFAULT_STARTING_LOCATION,size, horSpeed, verSpeed, col);
        setWeight(getSize() * 2.2);
        setNeckLength(DEFAULT_NECK_LENGTH);
        setDiet(new Herbivore());
        loadImages(animalShortPathName());
    }

    /**
     * Giraffe constructor.
     * setting default size.
     * passing name, horizontal speed and vertical speed to main constructor.
     * @param name , String value of the animal's name, should contain only letters.
     * @param horSpeed , Int value indicates animal's horizontal speed.
     * @param verSpeed , Int value indicates animal's vertical speed.
     */
    public Giraffe(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * Giraffe copy constructor
     * @param other Giraffe object to copy.
     */
    public Giraffe(Giraffe other){
        super(other);
        this.neckLength = other.getNeckLength();
    }

    /**
     * class Giraffe static method.
     * @return Point object of the default starting location of a Giraffe.
     */
    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    /**
     * neck length getter.
     * @return double value of the neck length.
     */
    public double getNeckLength() {
        return neckLength;
    }

    /**
     * class Giraffe static method.
     * @return double value of the default neck length for a Giraffe.
     */
    public static double getDefaultNeckLength() {
        return DEFAULT_NECK_LENGTH;
    }

    /**
     * class Giraffe static method
     * @return constant double value of the coefficient used for weight calculation.
     */
    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }

    /**
     * neck length setter.
     * checks if the neck length is valid (between min/max values).
     * if so, assigns the parameter value to the neckLength field.
     * otherwise, it will not update it.
     *
     * @param neckLength double value to set.
     * @return boolean value if set was successful or not.
     */
    public boolean setNeckLength(double neckLength) {
        boolean isSuccess = false;
        double MIN_LENGTH = 1.0, MAX_LENGTH = 2.5;
        if (neckLength >= MIN_LENGTH && neckLength <= MAX_LENGTH) {
            this.neckLength = neckLength;
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * chew implementation of a giraffe.
     */
    @Override
    public void chew() {
        System.out.println(this.getName() + " Bleats and Stomps its legs, then chews");
    }

    /**
     * equals method of Giraffe object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Giraffe giraffe = (Giraffe) o;

        return Double.compare(giraffe.getNeckLength(), getNeckLength()) == 0;
    }

    /**
     * toString of giraffe.
     * @return String representation of Giraffe.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Animal clone() {
        return new Giraffe(this);
    }

    /**
     * override abstract class Animal animalShortPathName().
     * @return String representation of the short path name for loading Giraffe images.
     */
    @Override
    public String animalShortPathName() {
        return "grf";
    }

    /**
     * override interface IAnimalBehavior getAnimalName().
     * @return String representation of the animal natural name.
     */
    @Override
    public String getAnimalName() {
        return "Giraffe";
    }

}