package com.factory;

import com.animals.Animal;

/**
 * IAnimalFactory is implemented by Factory classes who want to create an Animal object.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public interface IAnimalFactory {

    /**
     * createAnimal is a factory method used to create an Animal object depending on type.
     * @param animalType String representation of the animal type ("lion","bear", etc..)
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     * @return Animal object instantiated with given parameters.
     */
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col);
}
