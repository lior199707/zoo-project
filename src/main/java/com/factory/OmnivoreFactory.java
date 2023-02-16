package com.factory;

import com.animals.Animal;
import com.animals.Bear;

/**
 * class OmnivoreFactory, factory responsible for creating animals objects of type Omnivore.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class OmnivoreFactory implements IAnimalFactory {
    /**
     * @param animalType String representation of the animal type ("lion","bear", etc..)
     * @param name       String value of the animal's name, should contain only letters.
     * @param size       Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed   Int value indicates animal's horizontal speed.
     * @param verSpeed   Int value indicates animal's vertical speed.
     * @param col        String representing animal's color, "BLUE", "RED", "NATURAL".
     * @return Animal object (who's Diet is Omnivore) of the wanted animalType.
     */
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        if (animalType.equals("Bear")) {
            return new Bear(name, size, horSpeed, verSpeed, col);
        }
        return null;
    }
}
