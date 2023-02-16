package com.factory;

import com.animals.Animal;
import com.animals.Lion;

/**
 * CarnivoreFactory is a factory of carnivore animals.
 * it creates animals which can eat meat only such as Lions.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class CarnivoreFactory implements IAnimalFactory {

    /**
     * @param animalType String representation of the animal type ("lion","bear", etc..)
     * @param name String value of the animal's name, should contain only letters.
     * @param size Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed Int value indicates animal's horizontal speed.
     * @param verSpeed Int value indicates animal's vertical speed.
     * @param col String representing animal's color, "BLUE", "RED", "NATURAL".
     * @return Carnivore animal objects of the wanted animalType.
     */
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        if (animalType.equals("Lion")) {
            return new Lion(name, size, horSpeed, verSpeed, col);
        }
        return null;
    }
}
