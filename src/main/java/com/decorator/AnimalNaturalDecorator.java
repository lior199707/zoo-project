package com.decorator;

import com.animals.Animal;

/**
 * AnimalNaturalDecorator decorates the Animals with the natural color.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalNaturalDecorator extends AnimalColorDecorator {

    /**
     * AnimalNaturalDecorator constructor.
     * @param animal the animal to decorate.
     */
    public AnimalNaturalDecorator(Animal animal) {
        super(animal);
    }

    /**
     * decorate the animal with the natural color.
     * @return the Animal decorated object with the natural color.
     */
    @Override
    public Animal decorateAnimal() {
        setColor("NATURAL");
        return getAnimal();
    }
}