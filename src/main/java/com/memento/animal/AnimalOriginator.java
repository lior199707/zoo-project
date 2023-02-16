package com.memento.animal;

import com.animals.Animal;

import java.util.ArrayList;

/**
 * AnimalOriginator has the ability to create AnimalMemento objects.
 * responsible for creating new mementos in order to store them and storing the current state of the model.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalOriginator implements Cloneable {
    /**
     * ArrayList of animal objects representing the model.
     */
    private ArrayList<Animal> model;

    /**
     * Model setter.
     * @param model the ArrayList of animal objects to set.
     */
    public void setModel(ArrayList<Animal> model) { this.model = model; }

    /**
     * Model getter.
     * @return the ArrayList of animals object to get.
     */
    public ArrayList<Animal> getModel() { return model; }

    /**
     * createMemento creates a new ArrayList of animals and clones the animals from the originator's model.
     * @return AnimalMemento object with the cloned animal array.
     */
    public AnimalMemento createMemento() {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            animals.add(animal.clone());
        }
        return new AnimalMemento(animals);
    }

    /**
     * AnimalOriginator clone method.
     * @return AnimalOriginator cloned object.
     */
    @Override
    public AnimalOriginator clone() {
        AnimalOriginator clone = new AnimalOriginator();
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            animals.add(animal.clone());
        }
        clone.setModel(animals);
        return clone;
    }
}
