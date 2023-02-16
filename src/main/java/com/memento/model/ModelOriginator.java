package com.memento.model;

import com.animals.Animal;
import com.graphics.AnimalModel;

/**
 * ModelOriginator has the ability to create AnimalMemento objects.
 * responsible for creating new mementos in order to store them and storing the current state of the model.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class ModelOriginator implements Cloneable {
    /**
     * AnimalModel object representing the model.
     */
    private AnimalModel model;

    /**
     * Model setter.
     * @param model the AnimalModel object to set.
     */
    public void setModel(AnimalModel model) { this.model = model; }

    /**
     * Model getter.
     * @return the AAnimalModel object to get.
     */
    public AnimalModel getModel() { return model; }

    /**
     * createMemento creates a new AnimalModel clones the model state and the animals from the originator's model.
     * @return AnimalMemento object with the cloned animal array.
     */
    public ModelMemento createMemento() {
        AnimalModel temp = model.clone();
        // shuts down the model threads and stores the animals ArrayList of the model using the memento of the
        // animals ArrayList(AnimalCareTaker, AnimalMemento, AnimalOriginator).
        temp.saveModelState();
        return new ModelMemento(temp);
    }

    /**
     * ModelOriginator clone method.
     * @return ModelOriginator cloned object.
     */
    @Override
    public ModelOriginator clone() {
        ModelOriginator clone = new ModelOriginator();
        AnimalModel animals = new AnimalModel();
        for (Animal animal : model.getAnimalModel()){
            animals.addAnimal(animal.clone());
        }
        clone.setModel(animals);
        return clone;
    }
}
