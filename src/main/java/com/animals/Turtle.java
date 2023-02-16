package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;

/**
 * Turtle class representing the Turtle animal. It can chew!
 * @see com.animals.AnimalChew
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class Turtle extends AnimalChew {
    /**
     * constant double value of the default weight of a Turtle.
     */
    private static final double DEFAULT_WEIGHT = 1.0;
    /**
     * constant integer value of the default age of a Turtle.
     */
    private static final int DEFAULT_AGE = 1;
    /**
     * constant Point coordinates of the default starting location of a Turtle.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(80, 0);
    /**
     * constant double value of the coefficient used for weight calculation.
     */
    private static final double SIZE_COEFFICIENT = 0.5;
    /**
     * constant int value of the default size of a Turtle.
     */
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * integer value of the age of a Turtle.
     */
    private int age;


    /**
     * Turtle constructor.
     * setting default location, default age and diet!
     * passing name, location, size, horizontal speed, vertical speed and color to super.
     *
     * @see com.animals.AnimalChew
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public Turtle(String name, int size, int horSpeed, int verSpeed, String col) {
        super(name, DEFAULT_STARTING_LOCATION,size,horSpeed,verSpeed,col);
        setWeight(0.5 * getSize());
        setAge(DEFAULT_AGE);
        setDiet(new Herbivore());
        loadImages(animalShortPathName());
    }

    /**
     * Turtle constructor.
     * setting default size.
     * passing name, horizontal speed and vertical speed to main constructor.
     * @param name String value of the animal's name, should contain only letters.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     */
    public Turtle(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * Turtle copy constructor
     * @param other Turtle object to copy.
     */
    public Turtle(Turtle other){
        super(other);
        this.age = other.getAge();
    }

    /**
     * class Turtle static method.
     * @return Point object of the default starting location of a Turtle.
     */
    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    /**
     * age getter.
     * @return integer value of turtle age.
     */
    public int getAge() {
        return age;
    }

    /**
     * class Turtle static method.
     * @return int value of the default age for a Turtle.
     */
    public static int getDefaultAge() {
        return DEFAULT_AGE;
    }

    /**
     * class Turtle static method
     * @return constant double value of the coefficient used for weight calculation.
     */
    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }

    /**
     * age setter.
     * checks if the age is valid (between min/max values).
     * if so, assigns the parameter value to the age field.
     * otherwise, it will not update it.
     *
     * @param age integer value indicating turtle age.
     * @return boolean value if set was successful or not.
     */
    public boolean setAge(int age) {
        boolean isSuccess = false;
        int MIN_AGE = 0, MAX_AGE = 500;
        if (age >= MIN_AGE && age <= MAX_AGE) {
            this.age = age;
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * chew implementation of a turtle.
     */
    @Override
    public void chew() {
        System.out.println(getName() + " Retracts its head in then eats quietly");
    }

    /**
     * equals method of Turtle object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Turtle turtle = (Turtle) o;

        return getAge() == turtle.getAge();
    }

    /**
     * toString of elephant.
     * @return String representation of Turtle.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Animal clone() {
        return new Turtle(this);
    }

    /**
     * override abstract class Animal animalShortPathName().
     * @return String representation of the short path name for loading Turtle images.
     */
    @Override
    public String animalShortPathName() {
        return "trt";
    }

    /**
     * override interface IAnimalBehavior getAnimalName().
     * @return String representation of the animal natural name.
     */
    @Override
    public String getAnimalName() {
        return "Turtle";
    }

}