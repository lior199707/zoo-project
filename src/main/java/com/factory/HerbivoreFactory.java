package com.factory;

import com.animals.Animal;
import com.animals.Elephant;
import com.animals.Giraffe;
import com.animals.Turtle;

/**
 * class OmnivoreFactory, factory responsible for creating animals objects of type Herbivore.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class HerbivoreFactory implements IAnimalFactory {
    /**
     * @param animalType String representation of the animal type ("lion","bear", etc..)
     * @param name       String value of the animal's name, should contain only letters.
     * @param size       Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed   Int value indicates animal's horizontal speed.
     * @param verSpeed   Int value indicates animal's vertical speed.
     * @param col        String representing animal's color, "BLUE", "RED", "NATURAL".
     * @return Animal object (who's Diet is Herbivore) of the wanted animalType.
     */
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        switch (animalType) {
            case "Giraffe" -> { return new Giraffe(name, size, horSpeed, verSpeed, col); }
            case "Elephant" -> { return new Elephant(name, size, horSpeed, verSpeed, col); }
            case "Turtle" -> { return new Turtle(name, size, horSpeed, verSpeed, col); }
        }
        return null;
    }
}
