package com.decorator;

import com.animals.Animal;


/**
 * AnimalColorDecorator allows decorating an animal with a different color during run time.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public abstract class AnimalColorDecorator implements IColorDecorator {
    /**
     * Animal object to color.
     */
    private Animal animal;

    /**
     * AnimalColorDecorator constructor.
     * @param animal Animal object to color.
     */
    public AnimalColorDecorator(Animal animal) {
        this.animal = animal;
    }

    /**
     * Animal getter.
     * @return Animal object.
     */
    public Animal getAnimal() {
        return this.animal;
    }

    /**
     * Color setter.
     * sets the animal color and reload the animal's image.
     * @param color String representation of the animal's new color.
     */
    @Override
    public void setColor(String color) {
        animal.setColor(color);
        animal.loadImages(animal.animalShortPathName());
    }

    /**
     * Animal decorator.
     * @return the decorated Animal object.
     */
    public abstract Animal decorateAnimal();
}



