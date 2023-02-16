package com.animals;

import com.mobility.Point;

/**
 * AnimalRoar is an abstract class representing animals that can roar.
 * it is an Animal descendant.
 * @see com.animals.Animal
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class AnimalRoar extends Animal {

    /**
     * AnimalRoar constructor
     * passing values from descendant to Animal
     * @see com.animals.Animal
     *
     * @param name String value of the animal's name, should contain only letters.
     * @param location Point (x,y) indicating the location of the animal.
     *                  X coordinate valid range: 0-800.
     *                  Y coordinate valid range: 0-600.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public AnimalRoar(String name, Point location, int size, int horSpeed, int verSpeed, String col){
        super(name,location,size,horSpeed,verSpeed,col);
    }

    /**
     * AnimalRoar copy constructor
     * @param other AnimalRoar object to copy.
     */
    public AnimalRoar(AnimalRoar other){
        super(other);
    }

    /**
     * abstract method, need to be implemented in AnimalRoar descendants.
     */
    public abstract void roar();

    /**
     * makeSound activates the roar method.
     */
    @Override
    public void makeSound() {
        roar();
    }

    /**
     * equals of AnimalRoar object.
     * @param o the object to check equality.
     * @return boolean value if the objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


    /**
     * toString of AnimalRoar object.
     * @see com.animals.Animal toString() method.
     * @return String representation of the AnimalRoar object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
