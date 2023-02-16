package com.memento.animal;

import com.animals.Animal;

import java.util.ArrayList;

/**
 * AnimalMemento used as a state of ArrayList of animal objects.
 * responsible for storing states of the model.
 *
 * @author Sagie Baram 
 * @author Lior Shilon 
 */
public class AnimalMemento {
    /**
     * ArrayList of animal objects representing the model.
     */
    private ArrayList<Animal> model;

    /**
     * AnimalMemento constructor.
     * @param model ArrayList of animal objects representing the model.
     */
    public AnimalMemento(ArrayList<Animal> model){
        this.model = model;
    }

    /**
     * Model getter.
     * @return ArrayList of animal objects representing the model.
     */
    public ArrayList<Animal> getModel() { return model; }
}
