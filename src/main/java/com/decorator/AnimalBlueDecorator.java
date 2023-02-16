package com.decorator;

import com.animals.Animal;

/**
 * AnimalBlueDecorator decorates the Animals with the blue color.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class AnimalBlueDecorator extends AnimalColorDecorator {

    /**
     * AnimalBlueDecorator constructor.
     * @param animal the animal to decorate.
     */
    public AnimalBlueDecorator(Animal animal) {
        super(animal);
    }

    /**
     * decorate the animal with the blue color.
     * @return the Animal decorated object with the blue color.
     */
    @Override
    public Animal decorateAnimal() {
        setColor("BLUE");
        return getAnimal();
    }
}
