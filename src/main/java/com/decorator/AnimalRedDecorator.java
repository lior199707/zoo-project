package com.decorator;

import com.animals.Animal;

/**
 * AnimalRedDecorator decorates the Animals with the red color.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalRedDecorator extends AnimalColorDecorator {

    /**
     * AnimalRedDecorator constructor.
     * @param animal the animal to decorate.
     */
    public AnimalRedDecorator(Animal animal) {
        super(animal);
    }

    /**
     * decorate the animal with the red color.
     * @return the Animal decorated object with the red color.
     */
    @Override
    public Animal decorateAnimal() {
        setColor("RED");
        return getAnimal();
    }
}

